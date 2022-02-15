package com.gdev.moneymanager.Activity

import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdev.moneymanager.Adapter.Adapter_Expenses
import com.gdev.moneymanager.R
import com.gdev.moneymanager.databinding.ActivityMonthlyExpensesBinding
import com.pixplicity.easyprefs.library.Prefs

class Monthly_expenses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       var binding =  ActivityMonthlyExpensesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Prefs.Builder()
            .setContext(applicationContext)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

        val dates : Array<String> =   Prefs.getString("Date_of_day", "0").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
        val  exp : Array<String> =   Prefs.getString("Total_Exp_day", "0").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
          var recyclerView : RecyclerView = binding.rvAnay
        var adapter_Expenses = Adapter_Expenses(dates,exp,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter_Expenses
        var textmax = binding.textDateMaxExpMonExp
        textmax.text = exp.max().toString()
        var text_min = binding.minimumAnay
        text_min.text = exp.min().toString()
        var total_amount = 0
        for (i in 0 .. exp.size - 1){
            total_amount = total_amount + exp[i].toInt()
        }
        var text_avvg = binding.textavgmonth
        text_avvg.text =   (total_amount/exp.size).toString()



        var displaymatrix = DisplayMetrics()
         windowManager.defaultDisplay.getMetrics(displaymatrix)
        var x  = displaymatrix.widthPixels / 2 -40

        var linear_max =binding.linMaxMonth
        var linearmin = binding.linMinMonth



            var params = linear_max.layoutParams
            params.height =  200
            params.width = x
          linear_max.requestLayout()


        params = linearmin.layoutParams
        params.height =200
        params.width = x
        linearmin.requestLayout()





        var i = 0
        var index = 0
        for(i in 0 .. exp.size -1)
        {
            if(exp.max()!!.toInt() == exp[i].toInt()){
              index = i
            }
        }
        var text_date_max = binding.dateMaximumAnay
        text_date_max.text = dates[index]

         i = 0
        index = 0
        for(i in 0 .. exp.size -1)
        {
            if(exp.min()!!.toInt() == exp[i].toInt()){
                index = i
            }
        }
        var text_date_min = binding.dateMinimumAnay
           text_date_min.text = dates[index]


    }
}