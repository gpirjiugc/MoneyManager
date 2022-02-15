package com.gdev.moneymanager.Activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdev.moneymanager.Adapter.Adapter_Bank
import com.gdev.moneymanager.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import org.w3c.dom.Text

var i = 0
lateinit var aa: AlertDialog
lateinit var ab: AlertDialog
lateinit var conetext1: Context
lateinit var layoutInflater_1: LayoutInflater
class Balance_Check : AppCompatActivity() {

    companion object{
        fun show_messsage(data : String) {

            if(i==1){
                  aa.dismiss()

                  val bb = AlertDialog.Builder(conetext1)
                  val a4: View = layoutInflater_1.inflate(R.layout.sms_shower, null)
                  bb.setView(a4)
                  ab = bb.create()
                  ab.show()
                  var textView : TextView = a4.findViewById(R.id.text_msg) as TextView
                  textView.text = data

            }



        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance__check)
        layoutInflater_1 = layoutInflater
        conetext1 = this
         i = 1
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.CALL_PHONE, Manifest.permission.RECEIVE_SMS, ).withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) { /* ... */
                        if(report.areAllPermissionsGranted()){
                            dodo()
                        }
                        else {

                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) { /* ... */
                    }
                }).check()
        dodo()
    }

    private fun dodo() {





        val bb = AlertDialog.Builder(this)
        val ch: View = layoutInflater.inflate(R.layout.gerating_call, null)
        bb.setView(ch)

        aa = bb.create()

        aa.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var linearLayout: LinearLayout = findViewById(R.id.check_balance)
        var array = arrayOf(
                "1.STATE BANK OF INDIA ",
                "2.PUNJAB NATIONAL BANK",
                "3.CANARA BANK",
                "4.HDFC BANK",
                "5.CENTRAL BANK OF INDIA",
                "6.AXIS BANK",
                "7.IDBI BANK",
                "8.ICICI Bank",
                "9.BANK OF BARODA",
                "10.BANK OF INDIA",
                "11.UNITED BANK OF INDIA",
                "12.YES BANK",
                "13.KOTAK MAHINDRA BANK",
                "14.ANDHRA BANK",
                "15.SYNDICATE BANK"
        )
        var array_images = arrayOf(
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/sbi.jpg",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/pnb.png",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/cananra.jpg",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/HDFC.jpg",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/centralbank.png",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/Axis.png",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/IDBI.png",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/ICICI.png",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/BOB.png",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/BOI.png",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/UNBI.png",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/Yes.png",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/kotak.png",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/Andhra.jpg",
                "https://stethoscopic-instan.000webhostapp.com/money_manager_app_app/Sydn.png"
        )

        var adapterBank = Adapter_Bank(array_images, array, applicationContext)
        var rv = findViewById<RecyclerView>(R.id.rv_bank)
        rv.adapter = null
        rv.layoutManager = LinearLayoutManager(applicationContext)
        rv.adapter = adapterBank
        linearLayout.setOnClickListener {
            if (Adapter_Bank.string.equals("")) {
                Toast.makeText(this, "Select One", Toast.LENGTH_LONG).show()
            } else {


                aa.show()
                aa.window?.setLayout(400, 700)
                var textView: TextView = aa.findViewById<TextView>(R.id.text_gen_call) as TextView
                var anim: Animation = AlphaAnimation(0.2f, 1.0f)
                anim.duration = 500

                Handler().postDelayed(Runnable {
                    textView.setText("Bank:" + Adapter_Bank.string)
                    textView.animation = anim
                    Handler().postDelayed(Runnable {
                        textView.setText("Genrating Call")
                        textView.animation = anim

                        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getnumber()))
                        startActivity(intent)
                        textView.setText("Calling")

                        Handler().postDelayed(Runnable {
                            textView.setText("Waiting For Message ")
                            i = 1

                        }, 10000)


                    }, 5000)
                }, 5000)


            }
        }



    }

    private fun getnumber(): String {
        when(Adapter_Bank.string){
            "1.STATE BANK OF INDIA " -> return "09223766666"
            "2.PUNJAB NATIONAL BANK" -> return "18001802223"
            "3.CANARA BANK" -> return "09015483483"
            "4.HDFC BANK" -> return "18002703333"
            "5.CENTRAL BANK OF INDIA" -> return "9555244442"
            "6.AXIS BANK" -> return "18004195959"
            "7.IDBI BANK" -> return "18008431122"
            "8.ICICI Bank" -> return "9594612612"
            "9.BANK OF BARODA" -> return "8468001111"
            "10.BANK OF INDIA" -> return "09015135135"
            "11.UNITED BANK OF INDIA" -> return "18001802223"
            "12.YES BANK" -> return "09223920000"
            "13.KOTAK MAHINDRA BANK" -> return "1800 2707300"
            "14.ANDHRA BANK" -> return "09223011300"
            "15.SYNDICATE BANK" -> return return "9210332255"

        }
        return ""
    }


}
