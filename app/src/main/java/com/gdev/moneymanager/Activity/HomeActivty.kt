package com.gdev.moneymanager.Activity

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gdev.moneymanager.R
import com.gdev.moneymanager.databinding.ActivityHomeActivtyBinding
import com.gdev.moneymanager.fragments.frag_expenses
import com.gdev.moneymanager.fragments.frag_ledger
import com.gdev.moneymanager.fragments.home_frag
import me.ibrahimsn.lib.SmoothBottomBar
import java.lang.Exception


class HomeActivty : AppCompatActivity() {


   companion object{
       lateinit var bottomBar: SmoothBottomBar
       fun dochangeindex(i: Int) {
           bottomBar.itemActiveIndex = 1
       }
       fun invisiblebar (){
           bottomBar.visibility = View.INVISIBLE
       }
       fun visiblebar (){
           bottomBar.visibility = View.VISIBLE
       }


   }

    override fun onBackPressed() {
        bottomBar.itemActiveIndex  = 0
        bottomBar.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction().replace(R.id.frame1, home_frag()).commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityHomeActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            if(Intent().extras!!.getString("rcv").equals("per")){
                supportFragmentManager.beginTransaction().replace(R.id.frame1,frag_ledger()).commit()
            }
        }
        catch (e : Exception){

        }

            supportFragmentManager.beginTransaction().replace(R.id.frame1, home_frag()).commit()


        bottomBar = binding.bottomBar
        bottomBar.visibility = View.VISIBLE

        bottomBar.setOnItemSelectedListener {
            if(it==0){ supportFragmentManager.beginTransaction().replace(R.id.frame1, home_frag()).commit() }
            if(it==1){ supportFragmentManager.beginTransaction().replace(R.id.frame1, frag_expenses()).commit() }
            if(it==2){ supportFragmentManager.beginTransaction().replace(R.id.frame1, frag_ledger()).commit() }}
        window.statusBarColor = resources.getColor(R.color.mycolorback)

    }
}