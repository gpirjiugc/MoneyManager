package com.gdev.moneymanager.Activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdev.moneymanager.Adapter.Adapter_Bank_Server
import com.gdev.moneymanager.R
import com.gdev.moneymanager.databinding.ActivityServerCheckBinding

class Server_Check : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityServerCheckBinding.inflate(layoutInflater)


        setContentView(binding.root)
        window.statusBarColor = Color.WHITE
        var linearLayout: LinearLayout = binding.checkServer
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
        var adapterBank = Adapter_Bank_Server(array_images, array, applicationContext)
        var rv = binding.rvBankServer
        rv.adapter = null
        rv.layoutManager = LinearLayoutManager(applicationContext)
        rv.adapter = adapterBank
        linearLayout.setOnClickListener {
            if (Adapter_Bank_Server.string.equals("")) {
                Toast.makeText(this, "Select One", Toast.LENGTH_LONG).show()
            } else {
             Toast.makeText(applicationContext, Adapter_Bank_Server.string,Toast.LENGTH_LONG).show()
            }
        }

    }

}