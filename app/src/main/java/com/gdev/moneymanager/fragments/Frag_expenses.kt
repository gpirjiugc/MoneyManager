package com.gdev.moneymanager.fragments

import android.app.AlertDialog
import android.content.ContextWrapper
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gdev.moneymanager.Activity.HomeActivty
import com.gdev.moneymanager.Adapter.Adapter_Expense_loader
import com.gdev.moneymanager.R
import com.gdev.moneymanager.databinding.FragmentFragExpensesBinding
import com.nitish.typewriterview.TypeWriterView
import com.pixplicity.easyprefs.library.Prefs
import org.eazegraph.lib.models.PieModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class frag_expenses : Fragment() {








    lateinit  var binding : FragmentFragExpensesBinding
    var data = ""
    var data1 = ""
    lateinit var aa : AlertDialog
    lateinit var  add_shower : AlertDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentFragExpensesBinding.inflate(layoutInflater)
       intializingobjetcs()
        Prefs.putString("stateofapp","frag_exp")

        getessainsiational()
        addchart()
        loadexpenses()
        gettodaydatafornextdat()

        binding.bottomBarExp.setOnItemSelectedListener {
            when(it){

                0 -> {


                    for (fragment: Fragment in requireActivity().supportFragmentManager.fragments) {
                        requireActivity().supportFragmentManager.beginTransaction().remove(fragment)
                            .commit()
                    }
                    requireActivity().supportFragmentManager.beginTransaction().replace(
                        R.id.frame1,
                        frag_expenses()
                    ).commit()
                    binding.fmInternal.visibility = View.VISIBLE
                }

                1 -> {
                    binding.fmInternal.visibility = View.INVISIBLE
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fm_exp, calelndra_frag()).commit()
                }
            }
        }

        binding.cardAddExpenses.setOnClickListener {

          val bb = AlertDialog.Builder(requireContext())
            var view_al:View = layoutInflater.inflate(R.layout.dialog_add_expenses, null)
            bb.setView(view_al)

            aa = bb.create()
            aa.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            aa.show()






            var editText_name_exp: EditText = view_al.findViewById(R.id.name_exp) as EditText
            var editText_amount_exp: EditText = view_al.findViewById(R.id.amount_expt) as EditText
            var editText_des_exp: EditText = view_al.findViewById(R.id.dis_exp) as EditText
            var LinearLayout_button : LinearLayout = view_al.findViewById(R.id.linear_add_exp_dialog_button) as LinearLayout
            var spinner: Spinner = view_al.findViewById(R.id.spinner)


            var array = arrayOf("Shopping", "Home", "Medicine", "Restaurant", "Education", "Other")
            val a1: ArrayAdapter<*> = ArrayAdapter<Any?>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                array
            )


            a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.setAdapter(a1)
            spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    data = array[position]
                    when(position){
                        0 -> data1 = "https://i.ibb.co/KmBbZc6/shopping1.png"
                        1 -> data1 = "https://i.ibb.co/dKwqZn9/home.png"  //change
                        2 -> data1 = "https://i.ibb.co/W24D32W/medicine.png"  //change
                        3 -> data1 = "https://i.ibb.co/CwYm0YW/res.png"
                        4 -> data1 = "https://i.ibb.co/PWVczZH/education.png"  //change
                        5 -> data1 = "https://i.ibb.co/n83ydN1/others.png"

                    }


                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }


            LinearLayout_button.setOnClickListener{



               if (editText_amount_exp.text.isEmpty() || editText_name_exp.text.isEmpty() || editText_des_exp.text.isEmpty()||data.equals(
                       ""
                   ))
               {
                 Toast.makeText(
                     this.requireContext(),
                     "Field is Required To Fill",
                     Toast.LENGTH_LONG
                 ).show()
               }
               else {
                   val dateFormat: DateFormat = SimpleDateFormat("hh:mm a")
                   val formattedDate = dateFormat.format(Date()).toString()
                  aa.dismiss()
                   var string = editText_amount_exp.text.toString().replace(",", "")
                   addexpenses(
                       string,
                       editText_name_exp.text.toString(),
                       editText_des_exp.text.toString(),
                       data,
                       data1,
                       formattedDate
                   )


               }

           }


        }



        return  binding.root
    }

    private fun intializingobjetcs() {







      HomeActivty.invisiblebar()


        var displaymatrix = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displaymatrix)
        var x  = displaymatrix.widthPixels





        Prefs.Builder()
            .setContext(requireContext())
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(requireActivity().packageName)
            .setUseDefaultSharedPreference(true)
            .build()


        var params = binding.linearTypesExp.layoutParams
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        params.width =  x/2
        binding.linearTypesExp.requestLayout()

        params = binding.frameLinearChart.layoutParams
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        params.width = x/2
        binding.frameLinearChart.requestLayout()
    }

    private fun runtutorials() {
        val shower_1 = AlertDialog.Builder(requireContext())
        var view_shower :View = layoutInflater.inflate(R.layout.dialog_add_exp_viewer, null)

        shower_1.setView(view_shower)

        add_shower = shower_1.create()
        add_shower.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val window: Window? = add_shower.getWindow()
        val wlp: WindowManager.LayoutParams = window!!.getAttributes()

        val typeWriterView: TypeWriterView = view_shower.findViewById(R.id.text_type_1) as TypeWriterView
        typeWriterView.animateText("Click On this Button You can Add Expenses")


                wlp.gravity = Gravity.BOTTOM
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
        window.setAttributes(wlp)
        add_shower.show()
        var button1 : LinearLayout = view_shower.findViewById(R.id.linear_button_tut1) as LinearLayout
        button1.setOnClickListener {
            add_shower.dismiss()
            view_shower = layoutInflater.inflate(R.layout.dialog_add_exp_viewer1, null)
            shower_1.setView(view_shower)
            add_shower = shower_1.create()
            add_shower.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val window: Window? = add_shower.getWindow()
            val wlp: WindowManager.LayoutParams = window!!.getAttributes()
            wlp.gravity = Gravity.TOP
            wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
            window.setAttributes(wlp)
            add_shower.show()

            var button1 : LinearLayout = view_shower.findViewById(R.id.linear_button_tut2) as LinearLayout
            button1.setOnClickListener {
                add_shower.dismiss()
                view_shower = layoutInflater.inflate(R.layout.dialog_add_exp_viewer2, null)
                shower_1.setView(view_shower)
                add_shower = shower_1.create()
                add_shower.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                val window: Window? = add_shower.getWindow()
                val wlp: WindowManager.LayoutParams = window!!.getAttributes()
                wlp.gravity = Gravity.TOP
                wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
                window.setAttributes(wlp)
                add_shower.show()

                var button1 : LinearLayout = view_shower.findViewById(R.id.linear_button_tut3) as LinearLayout
                button1.setOnClickListener {
                    add_shower.dismiss()
                    view_shower = layoutInflater.inflate(R.layout.dialog_add_exp_viewer3, null)
                    shower_1.setView(view_shower)
                    add_shower = shower_1.create()
                    add_shower.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    val window: Window? = add_shower.getWindow()
                    val wlp: WindowManager.LayoutParams = window!!.getAttributes()
                    wlp.gravity = Gravity.TOP
                    wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
                    window.setAttributes(wlp)
                    add_shower.show()
                    var button1 : LinearLayout = view_shower.findViewById(R.id.linear_button_tut4) as LinearLayout
                    button1.setOnClickListener {
                        add_shower.dismiss()
                        view_shower = layoutInflater.inflate(R.layout.dialog_add_exp_viewer4, null)
                        shower_1.setView(view_shower)
                        add_shower = shower_1.create()
                        add_shower.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        val window: Window? = add_shower.getWindow()
                        val wlp: WindowManager.LayoutParams = window!!.getAttributes()
                        wlp.gravity = Gravity.TOP
                        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
                        window.setAttributes(wlp)
                        add_shower.show()
                        var button1 : LinearLayout = view_shower.findViewById(R.id.linear_button_tut5) as LinearLayout
                        button1.setOnClickListener { add_shower.dismiss()
                        Prefs.putString("Tut_Exp","2")
                        }






                    }




                }



            }





        }




    }

    private fun gettodaydatafornextdat() {
        val c = Calendar.getInstance().time
        println("Current time => $c")
        //amount - amount
        // name -> nameofexp
        // des -> des
        //exp_typ
        //date,mmmmmmmmmmmmmmmmmmmmm/
        //exp_image
        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedDate = df.format(c)


        if(Prefs.getString("nameofexp", "").equals("")){

        }
        else {
            if(Prefs.getString("update_date", "").equals("")){
                Prefs.putString("update_date", formattedDate)
            }
            else {
                if(Prefs.getString("update_date", "").equals(formattedDate)){
                    Prefs.putString(
                        formattedDate + "," + "nameofexp", Prefs.getString(
                            "nameofexp",
                            ""
                        )
                    )
                    Prefs.putString(formattedDate + "," + "des", Prefs.getString("des", ""))
                    Prefs.putString(formattedDate + "," + "exp_typ", Prefs.getString("exp_typ", ""))
                    Prefs.putString(formattedDate + "," + "date", Prefs.getString("date", ""))
                    Prefs.putString(
                        formattedDate + "," + "exp_image", Prefs.getString(
                            "exp_image",
                            ""
                        )
                    )
                    Prefs.putString(formattedDate + "," + "amount", Prefs.getString("amount", ""))
                    Toast.makeText(requireContext(), "Current Day", Toast.LENGTH_LONG).show()
                }
                else {

                    if(Prefs.getString("Date_of_day", "").equals("")&&Prefs.getString(
                            "Total_Exp_day",
                            ""
                        ).equals("")){
                        Prefs.putString("Date_of_day", formattedDate)
                        val amount_a : Array<String?> =   Prefs.getString("amount", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
                        var total_spend  = 0

                        for(i in 0 ..  amount_a.size-1){
                            total_spend = amount_a[i].toString().toInt() + total_spend
                        }
                        Prefs.putString("Total_Exp_day", total_spend.toString())
                    }
                    else{
                        Prefs.putString(
                            "Date_of_day", Prefs.getString(
                                "Date_of_day",
                                ""
                            ) + "," + formattedDate
                        )
                        val amount_a : Array<String?> =   Prefs.getString("amount", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
                        var total_spend  = 0

                        for(i in 0 ..  amount_a.size-1){
                            total_spend = amount_a[i].toString().toInt() + total_spend
                        }
                        Prefs.putString(
                            "Total_Exp_day", Prefs.getString(
                                "Total_Exp_day",
                                total_spend.toString()
                            ) + "," + total_spend.toString()
                        )
                    }

                    Toast.makeText(requireContext(), "New Day", Toast.LENGTH_LONG).show()
                    //amount - amount
                    // name -> nameofexp
                    // des -> des
                    //exp_typ
                    //date
                    //exp_image
                    Prefs.putString("amount", "")
                    Prefs.putString("nameofexp", "")
                    Prefs.putString("des", "")
                    Prefs.putString("exp_typ", "")
                    Prefs.putString("date", "")
                    Prefs.putString("exp_image", "")

                    Prefs.putString("update_date", formattedDate)



                }

            }


        }


    }

    private fun addchart() {
        var shoping_amount = 0
        var home_amount = 0
        var medicine_amount = 0
        var res_amount = 0
        var education_amount = 0
        var other = 0

        val amounts : Array<String?> =   Prefs.getString("amount", "").split("[,]".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
        val exp_types : Array<String?> =   Prefs.getString("exp_typ", "").split("[,]".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()

        for(i in 0..amounts.size-1){
            if(exp_types[i].toString().equals("Shopping")){
                shoping_amount = amounts[i].toString().toInt() + shoping_amount
            }
            if(exp_types[i].toString().equals("Home")){
                home_amount = amounts[i].toString().toInt() + home_amount
            }

            if(exp_types[i].toString().equals("Medicine")){
                medicine_amount = amounts[i].toString().toInt() + medicine_amount
            }
            if(exp_types[i].toString().equals("Restaurant")){
               res_amount = amounts[i].toString().toInt() + res_amount
            }
            if(exp_types[i].toString().equals("Education")){
                education_amount = amounts[i].toString().toInt() + education_amount
            }
            if(exp_types[i].toString().equals("Other")){
             other = amounts[i].toString().toInt() + other
            }

          binding.piechart.clearChart()
              binding.piechart.addPieSlice(
                  PieModel(
                      "Shopping",
                      shoping_amount.toFloat(),
                      Color.parseColor("#3C99EA")
                  )
              )
            binding.piechart.addPieSlice(
                PieModel(
                    "Home",
                    home_amount.toFloat(),
                    Color.parseColor("#24E40F")
                )
            ) // ok
            binding.piechart.addPieSlice(
                PieModel(
                    "Medicine",
                    medicine_amount.toFloat(),
                    Color.parseColor("#FA3A3A")
                )
            )
            binding.piechart.addPieSlice(
                PieModel(
                    "Restaurant",
                    res_amount.toFloat(),
                    Color.parseColor("#EF711C")
                )
            )
            binding.piechart.addPieSlice(
                PieModel(
                    "Education", education_amount.toFloat(), Color.parseColor(
                        "#8351F6"
                    )
                )
            )
            binding.piechart.addPieSlice(PieModel("Others", other.toFloat(), Color.parseColor("#888888")))
            binding.piechart.startAnimation()


        }
    }

    private fun getessainsiational() {
        Glide.with(requireActivity()).load(Prefs.getString("image")).into(binding.imageHomeProfile)
        val amount_a : Array<String?> =   Prefs.getString("amount", "").split("[,]".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
        var total_spend : Int = 0
        for(i in 0 ..  amount_a.size-1){
            total_spend = amount_a[i].toString().toInt() + total_spend
        }
        binding.totalSpend.setText(total_spend.toString())
        binding.totalBalance.setText(Prefs.getString("balance", ""))
        if(Prefs.getString("Tut_Exp","").equals("")){
            Prefs.putString("Tut_Exp","1")
            runtutorials()
        }
        else {
            Prefs.putString("Tut_Exp","2")

        }

    }

    private fun loadexpenses() {
       if(Prefs.getString("nameofexp", "").equals("")){

        }
         else {
           //amount - amount
           // name -> nameofexp
           // des -> des
           //exp_typ
           //date
           //exp_image
            binding.rvExp.adapter = null

           val names_exp : Array<String> =   Prefs.getString("nameofexp", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
           val des : Array<String> =   Prefs.getString("des", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
           val exp_type : Array<String> =   Prefs.getString("exp_typ", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
           val time : Array<String> =   Prefs.getString("date", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
           val exp_image : Array<String> =   Prefs.getString("exp_image", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
           val amount : Array<String> =   Prefs.getString("amount", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()

           var Adapter = Adapter_Expense_loader(amount, names_exp, des, exp_type, exp_image, time, requireContext())
           binding.rvExp.layoutManager = LinearLayoutManager(requireContext())
           binding.rvExp.adapter = Adapter

           addchart()



       }
    }

    private fun addexpenses(amount: String, nameofexp: String, description: String, exp_type: String, exp_image: String, formattedDate: String) {

        var h = Prefs.getString("balance").toString()
        h = h.replace(",", "")

        if(h.toInt() <= amount.toInt() ){
            Toast.makeText(
                requireContext(),
                "Enough Balance Pls Add Some Balance ",
                Toast.LENGTH_LONG
            ).show()
        }
        else {
            //amount - amount
            // name -> nameofexp
             // des -> des
            //exp_typ
            //date
            //exp_image


           val net : Int =   h.toInt() - amount.toInt()
           Prefs.putString("balance", net.toString())

            if(Prefs.getString("nameofexp", "").equals("")||Prefs.getString("des", "").equals("")
                    ||Prefs.getString("exp_typ", "").equals("")
                    ||Prefs.getString("date", "").equals("")||Prefs.getString("exp_image", "").equals(
                    ""
                )||
                    Prefs.getString("amount", "").equals(""))
            {

                Prefs.putString("amount", amount)
                Prefs.putString("nameofexp", nameofexp)
                Prefs.putString("des", description)
                Prefs.putString("exp_typ", exp_type)
                Prefs.putString("date", formattedDate)
                Prefs.putString("exp_image", exp_image)

                binding.totalSpend.setText(amount)
                binding.totalBalance.setText(Prefs.getString("balance", ""))


            }
            else {

                Prefs.putString("amount", Prefs.getString("amount") + "," + amount)
                Prefs.putString("nameofexp", Prefs.getString("nameofexp") + "," + nameofexp)
                Prefs.putString("des", Prefs.getString("des") + "," + description)
                Prefs.putString("exp_typ", Prefs.getString("exp_typ") + "," + exp_type)
                Prefs.putString("date", Prefs.getString("date") + "," + formattedDate)
                Prefs.putString("exp_image", Prefs.getString("exp_image") + "," + exp_image)


                val amount_a : Array<String?> =   Prefs.getString("amount", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
                var total_spend  = 0

                for(i in 0 ..  amount_a.size-1){
                    total_spend = amount_a[i].toString().toInt() + total_spend
                }
                binding.totalBalance.setText(Prefs.getString("balance", ""))
                binding.totalSpend.setText(total_spend.toString())
                loadexpenses()
            }

        }

    }    }