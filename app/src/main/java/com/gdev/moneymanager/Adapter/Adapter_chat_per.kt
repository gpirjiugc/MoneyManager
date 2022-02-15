package com.gdev.moneymanager.Adapter

import android.content.Context
import android.graphics.Color
import android.media.Image
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gdev.moneymanager.R
import org.w3c.dom.Text

class Adapter_chat_per(Amount : Array<String>, Side : Array<String>, context1: Context , X : Int,Time : Array<String>, Y : Int,Date: Array<String>) : RecyclerView.Adapter<Adapter_chat_per.abc>() {
    var amount : Array<String>
    var side : Array<String>
    var x : Int
    var date : Array<String>
    var y : Int
    var time : Array<String>

    var context : Context? = null
    init {
        time = Time
       amount =Amount
        side = Side
        date = Date
        context = context1
        x = X
        y = Y
    }
    class abc(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_name :  TextView = itemView.findViewById(R.id.amount_chat)
        var parent_lay :FrameLayout = itemView.findViewById(R.id.parent_id_intsance)
        var  card : LinearLayout = itemView.findViewById(R.id.card_chat)
        var time_text : TextView = itemView.findViewById(R.id.time_chat)
        var date_text : TextView = itemView.findViewById(R.id.date_chat)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): abc {
        val l = LayoutInflater.from(parent.context)
        val v: View = l.inflate(R.layout.instance_of_ledger, parent, false)
        return abc(v)
    }

    override fun onBindViewHolder(holder: abc, position: Int) {
       if(side[position].equals("start")){
           holder.text_name.setTextColor(Color.parseColor("#C14324"))
           var params =  FrameLayout.LayoutParams(x/3 ,150).apply { gravity = Gravity.START }
           holder.card.layoutParams = params
           holder.card.setBackgroundDrawable(ContextCompat.getDrawable(context!!,R.drawable.back_card_chat1))
       }
        if(side[position].equals("end")){
            holder.text_name.setTextColor(Color.parseColor("#079F45"))

            var params =  FrameLayout.LayoutParams(x/3 ,150).apply { gravity = Gravity.END }
            holder.card.layoutParams = params
            holder.card.setBackgroundDrawable(ContextCompat.getDrawable(context!!,R.drawable.back_card_chat))
        }
        holder.text_name.text = amount[position]

        holder.date_text.text =date[position]
        holder.time_text.text = time[position]

        var params = holder.card.layoutParams

        params.width =  x/2 - 60

        params.height = y/100 * 12


        holder.card.requestLayout()
        params = holder.parent_lay.layoutParams
        params.width =  WindowManager.LayoutParams.MATCH_PARENT

        params.height = y/100 * 12 + 20


        holder.parent_lay.requestLayout()



    }

    override fun getItemCount(): Int {
    return amount.size
    }

}