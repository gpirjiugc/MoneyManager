package com.gdev.moneymanager.Activity

import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.gdev.moneymanager.R
import com.pixplicity.easyprefs.library.Prefs


class Form : AppCompatActivity() {
lateinit var aa : AlertDialog
var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        var imageView : ImageView =findViewById(R.id.image_form)
        var textView : TextView  = findViewById(R.id.Select_image_Form)
       var edittext_name : EditText = findViewById(R.id.Edit_Form_Name)
        var editText_number : EditText = findViewById(R.id.Edit_Form_Number)
        var editText_bal : EditText = findViewById(R.id.Edit_Form_Bal)
        var button : LinearLayout = findViewById(R.id.button_form)
        var editText_profession = findViewById<EditText>(R.id.Edit_Form_Profession)
        window.statusBarColor = Color.parseColor("#2B88C6")


        val bb = AlertDialog.Builder(this)
        val ch: View = layoutInflater.inflate(R.layout.dialog_selecter_profile, null)
        bb.setView(ch)
        aa = bb.create()
        aa.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var displaymatrix = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displaymatrix)
        var x  = displaymatrix.widthPixels / 100 * 75



        var card_select_profile : CardView = ch.findViewById(R.id.card_selecter_profiile)
        var imageView1 : ImageView = ch.findViewById(R.id.profiel_select_dialog_1) as ImageView
        var imageView2 : ImageView = ch.findViewById(R.id.profiel_select_dialog_2) as ImageView
        var imageView3 : ImageView = ch.findViewById(R.id.profiel_select_dialog_3) as ImageView
        var imageView4 : ImageView = ch.findViewById(R.id.profiel_select_dialog_4) as ImageView
        var imageView5 : ImageView = ch.findViewById(R.id.profiel_select_dialog_5) as ImageView
        var imageView6 : ImageView = ch.findViewById(R.id.profiel_select_dialog_6) as ImageView
        var imageView7 : ImageView = ch.findViewById(R.id.profiel_select_dialog_7) as ImageView
        var imageView8 : ImageView = ch.findViewById(R.id.profiel_select_dialog_8) as ImageView
        var imageView9 : ImageView = ch.findViewById(R.id.profiel_select_dialog_9) as ImageView
        var imageView10 : ImageView = ch.findViewById(R.id.profiel_select_dialog_10) as ImageView
        var imageView11 : ImageView = ch.findViewById(R.id.profiel_select_dialog_11) as ImageView
        var imageView12 : ImageView = ch.findViewById(R.id.profiel_select_dialog_12) as ImageView


        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()


         button.setOnClickListener {
         if (editText_number.text.isEmpty() || edittext_name.text.isEmpty() || id.equals("") || editText_bal.text.isEmpty()||editText_profession.text.isEmpty()) {
             Toast.makeText(this, "Fill Is Required ", Toast.LENGTH_LONG).show()
         } else {

             if(editText_number.text.toString().length <= 9){
                 Toast.makeText(this,"Number Must Be 10 Digit ",Toast.LENGTH_SHORT).show()
             }
             else {

             Prefs.putString("name", edittext_name.text.toString())
             Prefs.putString("number", editText_number.text.toString())
             Prefs.putString("image", id)
             Prefs.putString("balance", editText_bal.text.toString())
             Prefs.putString("profession",editText_profession.text.toString())
             Toast.makeText(this, "welcome", Toast.LENGTH_LONG).show()
             Prefs.putString("status_of_form","true")
             startActivity(Intent(this, HomeActivty::class.java)) }
         }
     }
        imageView1.setOnClickListener {
            imageView.setImageDrawable(imageView1.drawable)
            id = "https://i.ibb.co/BsX1RDr/icon-profile-1.png"
            aa.dismiss()
        }
        imageView2.setOnClickListener {
            imageView.setImageDrawable(imageView2.drawable)
            id = " https://i.ibb.co/Trm3Dhk/icon-profile-2.png"
            aa.dismiss()
        }
        imageView3.setOnClickListener {
            imageView.setImageDrawable(imageView3.drawable)
            id = "https://i.ibb.co/qd0QgLD/icon-profile-3.png"
            aa.dismiss()
        }
        imageView4.setOnClickListener {
            imageView.setImageDrawable(imageView4.drawable)
            id = "https://i.ibb.co/Ltqs90V/icon-profile-4.png"
            aa.dismiss()
        }
        imageView5.setOnClickListener {
            imageView.setImageDrawable(imageView5.drawable)
            id = "https://i.ibb.co/T2kzg9v/icon-profile-5.png"
        }
        imageView6.setOnClickListener {
            imageView.setImageDrawable(imageView6.drawable)
            id = "https://i.ibb.co/6DTLpyy/icon-profile-6.png"
            aa.dismiss()
        }
        imageView7.setOnClickListener {
            imageView.setImageDrawable(imageView7.drawable)
            id = "https://i.ibb.co/17YncVw/icon-profile-7.png"
            aa.dismiss()
        }
        imageView8.setOnClickListener {
            imageView.setImageDrawable(imageView8.drawable)
            id = "https://i.ibb.co/grwdbFL/icon-profile-8.png"
            aa.dismiss()
        }
        imageView9.setOnClickListener {
            imageView.setImageDrawable(imageView9.drawable)
            id = "https://i.ibb.co/n0CQ6N4/icon-profile-9.png"
            aa.dismiss()
        }
        imageView10.setOnClickListener {
            imageView.setImageDrawable(imageView10.drawable)
            id = "https://i.ibb.co/54T09DR/icon-profile-10.png"

            aa.dismiss()
        }
        imageView11.setOnClickListener {
            imageView.setImageDrawable(imageView11.drawable)
            id = "https://i.ibb.co/NC7jXQz/icon-profile-11.png"
            aa.dismiss()
        }
        imageView12.setOnClickListener {
            imageView.setImageDrawable(imageView12.drawable)
            id = "https://i.ibb.co/Hh4hrTJ/icon-profile-12.png"
            aa.dismiss()
        }
        textView.setOnClickListener(View.OnClickListener {
            aa.show()
            var params =card_select_profile.layoutParams
            params.width = x
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            card_select_profile.requestLayout()

        })
    }
}