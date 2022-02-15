package com.gdev.moneymanager.Activity

import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.gdev.moneymanager.R
import com.pixplicity.easyprefs.library.Prefs

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.WHITE

        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        Handler().postDelayed(Runnable {  if(Prefs.getString("status_of_form","").equals("true")){
            startActivity( Intent(this, HomeActivty::class.java))
        }
            else {
            startActivity( Intent(this, Slider_View::class.java))
        }

                                       },5000)
        var linear = findViewById<LinearLayout>(R.id.linear_splash)
        var anim: Animation = AlphaAnimation(0.1f, 1.0f)
        anim.duration = 1000
        linear.animation = anim


    }
}