package com.gdev.moneymanager.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gdev.moneymanager.R
import de.hdodenhof.circleimageview.CircleImageView


class Adapter_Expense_loader(amount1: Array<String>, name1: Array<String>, des1: Array<String>, exp_typ1: Array<String>, exp_image1: Array<String>, time1: Array<String>, context1: Context) : RecyclerView.Adapter<Adapter_Expense_loader.abc>() {
    var amount : Array<String>
    var names : Array<String>
    var des : Array<String>
    var exp_typ : Array<String>
    var exp_image : Array<String>
    var time : Array<String>
    var context : Context

    init {
        names = name1
        amount = amount1
        des = des1
         exp_image = exp_image1
        exp_typ = exp_typ1
       time = time1
        context = context1
    }
    class abc(itemView: View) : RecyclerView.ViewHolder(itemView){
        var Text_name : TextView = itemView.findViewById(R.id.type_exp_ins)
        var Text_amount : TextView = itemView.findViewById(R.id.amount_exp_ins)
        var Text_des : TextView = itemView.findViewById(R.id.des_of_exp_ins)
        var Text_type : TextView = itemView.findViewById(R.id.nameofexp_ins)
        var image : CircleImageView = itemView.findViewById(R.id.image_ins_exp)

        var Text_time : TextView  = itemView.findViewById(R.id.time_ofexp_ins)



    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): abc {
        val l = LayoutInflater.from(parent.context)
        val v: View = l.inflate(R.layout.instance_of_expenses, parent, false)
        return abc(v)
    }

    override fun onBindViewHolder(holder: abc, position: Int) {
     holder.Text_name.setText(names[position])
        holder.Text_amount.setText(amount[position])
        holder.Text_des.setText(des[position])

  Glide.with(context).load(exp_image[position]).into(holder.image)
        holder.Text_type.setText(exp_typ[position])
        holder.Text_time.setText(time[position])
    }

    override fun getItemCount(): Int {
        return names.size
    }

}