package com.gdev.moneymanager.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdev.moneymanager.R

class Adapter_Expenses(dates1 : Array<String>,expenses_amount1 : Array<String>,context1: Context) : RecyclerView.Adapter<Adapter_Expenses.abc>() {
  var dates : Array<String>
  var expenses_amount : Array<String>
    var context : Context? = null
    init {
        dates = dates1
        expenses_amount = expenses_amount1
        context = context1
    }
    class abc(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_exp :  TextView = itemView.findViewById(R.id.text_amount_anay)
        var dates : TextView = itemView.findViewById(R.id.text_date_instance_anay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): abc {
        val l = LayoutInflater.from(parent.context)
        val v: View = l.inflate(R.layout.instance_of_perday, parent, false)
        return abc(v)
    }

    override fun onBindViewHolder(holder: abc, position: Int) {
     holder.dates.setText(dates[position])
     holder.text_exp.setText(expenses_amount[position])
    }

    override fun getItemCount(): Int {
    return dates.size
    }

}