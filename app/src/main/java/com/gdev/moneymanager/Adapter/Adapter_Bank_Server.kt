package com.gdev.moneymanager.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gdev.moneymanager.R

class Adapter_Bank_Server(images_c : Array<String>, names_C : Array<String>, context1 : Context) : RecyclerView.Adapter<Adapter_Bank_Server.abc>() {

    companion object{
        var string = ""
        var o = 0

    }

  init {
       images = images_c
       names = names_C
      context = context1
  }

    class abc(itemView: View) : RecyclerView.ViewHolder(itemView) {
          var imageView = itemView.findViewById<ImageView>(R.id.image_bank)
          var textView = itemView.findViewById<TextView>(R.id.text_bank_name)
          var card = itemView.findViewById<LinearLayout>(R.id.card_bank)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): abc {
        val l = LayoutInflater.from(parent.context)
        val v: View = l.inflate(R.layout.instance_bankes, parent, false)
        return abc(v)
    }

    override fun onBindViewHolder(holder: abc, position: Int) {

        holder.textView.setText(names[position])
        holder.card.background.setTint(context!!.resources.getColor(R.color.mycolorback))
        context?.let { Glide.with(it).load(images[position]).into(holder.imageView) }
        holder.card.setOnClickListener {

            if(o ==0)  {
                holder.card.background.setTint(context!!.resources.getColor(R.color.mycolorback))
            o = 1

              string = names[position]
            holder.card.background.setTint(Color.parseColor("#04053A")) }

            else {
                holder.card.background.setTint(context!!.resources.getColor(R.color.mycolorback))
                    string = ""
                    o = 0
            }

        }
    }

    override fun getItemCount(): Int {
        return names.size
    }
}