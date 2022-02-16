package com.gdev.moneymanager.fragments

import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gdev.moneymanager.Activity.Activity_Selecter_Contact
import com.gdev.moneymanager.Activity.HomeActivty
import com.gdev.moneymanager.Adapter.Adapter_list_per
import com.gdev.moneymanager.R
import com.gdev.moneymanager.databinding.FragmentFragLedgerBinding
import com.pixplicity.easyprefs.library.Prefs


class frag_ledger : Fragment() {
    var totalamount_min = 0
    var totalamount_plus = 0


    private fun getamounts() {
        Prefs.Builder()
                .setContext(context)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(requireActivity().packageName)
                .setUseDefaultSharedPreference(true)
                .build()
        val name : Array<String> =   Prefs.getString("Contact_Name", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()


        var amounts = arrayOfNulls<Int>(name.size)

        var totalamount = 0


        for(i in name.indices){
            totalamount = 0
            val amount : Array<String> =   Prefs.getString("chat_" + name[i] + "_amount", "0").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
            for (u in amount.indices){
                totalamount = amount[u].toInt() + totalamount
            }
            amounts[i] = totalamount

        }


        for (a in amounts.indices){

            if(amounts[a]!!.toInt() > 0){
                totalamount_plus = amounts[a]!!.toInt() + totalamount_plus
            }

            if(amounts[a]!!.toInt() < 0){
                 totalamount_min = amounts[a]!!.toInt() + totalamount_min
            }


        }









    }
  lateinit var binding  :  FragmentFragLedgerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
         binding = FragmentFragLedgerBinding.inflate(layoutInflater)

        HomeActivty.invisiblebar()



        Prefs.Builder()
                .setContext(context)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(requireActivity().packageName)
                .setUseDefaultSharedPreference(true)
                .build()
        getamounts()
        binding.textCreditLedgerHome.text =totalamount_plus.toString()

        binding.textNetLedgerHome.text = (totalamount_plus - totalamount_min).toString()

        binding.textDueLedgerHome.text = totalamount_min.toString()
        binding.cardAddPer.setOnClickListener { startActivity(Intent(requireContext(),Activity_Selecter_Contact::class.java)) }
        binding.textNameLedgerHome.text = Prefs.getString("name","")
        binding.textProfessionLedgerHome.text  = Prefs.getString("profession","")
        Glide.with(this).load(Prefs.getString("image","")).into(binding.imageHomeProfileLedger)







        loadlisofper()



        return  binding.root
    }

    private fun loadlisofper() {
        val contactsname : Array<String> =   Prefs.getString("Contact_Name", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
        val nicknames : Array<String> =   Prefs.getString("Nick_Name", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
        val contactnumber : Array<String> =   Prefs.getString("Contact_Number", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
        val contactprofile : Array<String> =   Prefs.getString("Contact_Profile", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()

        var adapter =  Adapter_list_per(contactsname,nicknames,contactprofile,contactnumber,requireContext() )
        binding.rvListPersons.layoutManager =  LinearLayoutManager(context)
        binding.rvListPersons.adapter = adapter
    }


}