package com.gdev.moneymanager.Adapter

import android.content.Context
import android.content.ContextWrapper
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gdev.moneymanager.R
import com.gdev.moneymanager.fragments.Frag_chat
import com.gdev.moneymanager.fragments.frag_ledger
import com.pixplicity.easyprefs.library.Prefs
import org.w3c.dom.Text
import java.util.logging.Handler

class Adapter_list_per(Name : Array<String>, NickName : Array<String>, Images : Array<String>, Contact : Array<String>,context1: Context) : RecyclerView.Adapter<Adapter_list_per.abc>() {
    var name : Array<String>
    var nickname : Array<String>
    var images : Array<String>
    var contact : Array<String>
    lateinit var amounts : Array<Int?>



    var context : Context? = null
    init {
       name = Name
        contact = Contact
        nickname = NickName
        images = Images
        context = context1
        getamounts()


    }

    private fun getamounts() {
        Prefs.Builder()
                .setContext(context!!)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(context!!.packageName)
                .setUseDefaultSharedPreference(true)
                .build()


        amounts = arrayOfNulls(name.size)

        var totalamount = 0

//        for(i in name.indices){
//
//            val amount : Array<String> =   Prefs.getString("chat_" + name[i] + "_amount", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
//
//
////            for(i in amount.indices) totalamount += amount[i].toInt()
////            amounts[i] = totalamount
//        }
//

        for(i in name.indices){
            totalamount = 0
            val amount : Array<String> =   Prefs.getString("chat_" + name[i] + "_amount", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
            for (u in amount.indices){
                totalamount = amount[u].toInt() + totalamount
            }
            amounts[i] = totalamount

        }




    }

    class abc(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_name :  TextView = itemView.findViewById(R.id.nameofper)
        var image_per : ImageView = itemView.findViewById(R.id.profile_person_list)
        var  nickname_per : TextView = itemView.findViewById(R.id.per_relation)
        var textamount : TextView = itemView.findViewById(R.id.amount_of_per)
        var card : CardView = itemView.findViewById(R.id.card_per_list)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): abc {
        val l = LayoutInflater.from(parent.context)
        val v: View = l.inflate(R.layout.instance_of_person, parent, false)
        return abc(v)
    }

    override fun onBindViewHolder(holder: abc, position: Int) {
        Prefs.Builder()
                .setContext(context!!)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(context!!.packageName)
                .setUseDefaultSharedPreference(true)
                .build()

     holder.nickname_per.text = nickname[position]
        context?.let { Glide.with(it).load(images[position]).into(holder.image_per) }
        holder.text_name.text = name[position]
        if(amounts[position]!! > 0){
            holder.textamount.setTextColor(context!!.resources.getColor(R.color.green))
        }
        if(amounts[position]!! < 0){
            holder.textamount.setTextColor(context!!.resources.getColor(R.color.red))
        }

          holder.textamount.setText(amounts[position].toString())




        holder.card.setOnClickListener { if(Prefs.getString("current_chat_name","").equals("")){
            Prefs.putString("current_chat_name",name[position].toString())
            Prefs.putString("current_chat_nick_name",nickname[position])
            Prefs.putString("current_chat_image",images[position])
            Prefs.putString("current_chat_contact",contact[position])

           var aas : AppCompatActivity  = context as AppCompatActivity
            aas.supportFragmentManager.beginTransaction().replace(R.id.frame1,Frag_chat()).commit()

        }
            else {

            Prefs.putString("current_chat_name",name[position].toString())
            Prefs.putString("current_chat_nick_name",nickname[position])
            Prefs.putString("current_chat_image",images[position])
            Prefs.putString("current_chat_contact",contact[position])
            var aas : AppCompatActivity  = context as AppCompatActivity

            aas.supportFragmentManager.beginTransaction().replace(R.id.frame1,Frag_chat()).commit()
        }
        }

      }

    override fun getItemCount(): Int {
    return name.size
    }

}