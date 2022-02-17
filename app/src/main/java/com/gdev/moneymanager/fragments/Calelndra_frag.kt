 package com.gdev.moneymanager.fragments

import android.content.ContextWrapper
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdev.moneymanager.Adapter.Adapter_Expense_loader
import com.gdev.moneymanager.databinding.FragmentCalelndraFragBinding
import com.pixplicity.easyprefs.library.Prefs
import org.eazegraph.lib.models.PieModel
import java.text.SimpleDateFormat
import java.util.*


class calelndra_frag : Fragment() {

   lateinit var binding : FragmentCalelndraFragBinding

   var mon = ""
    var dated = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

         binding = FragmentCalelndraFragBinding.inflate(layoutInflater)


        val c = Calendar.getInstance().time
        println("Current time => $c")

        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedDate = df.format(c)
         dated = formattedDate

          loadexpense()
          loadpeichart()

        binding.clickCheckDate.setOnClickListener{
            if(binding.dateEt.text.isEmpty()){
                Toast.makeText(requireContext(), "Date is Required", Toast.LENGTH_LONG).show()
            }
            if(binding.monthEt.text.isEmpty()){
                Toast.makeText(requireContext(), "Month is Required", Toast.LENGTH_LONG).show()
            }
            if(binding.yearEt.text.isEmpty()){
                Toast.makeText(requireContext(), "Year is Required", Toast.LENGTH_LONG).show()
            }
            else {
                if(binding.yearEt.text.length>=2&&binding.monthEt.text.length>=2&&binding.yearEt.text.length>=4){




                    when(binding.monthEt.text.toString()){
                        "01" -> mon = "Jan"
                        "02" -> mon = "Feb"
                        "03" -> mon = "Mar"
                        "04" -> mon = "Apr"
                        "05" -> mon = "May"
                        "06" -> mon = "Jun"
                        "07" -> mon = "Jul"
                        "08" -> mon = "Aug"
                        "09" -> mon = "Sep"
                        "10" -> mon = "Oct"
                        "11" -> mon = "Nov"
                        "12" -> mon = "Dec"
                    }
                    dated = binding.dateEt.text.toString() +"-"+mon+"-"+binding.yearEt.text.toString()



                    Toast.makeText(requireContext(), dated, Toast.LENGTH_LONG).show()
                    loadexpense()
                    loadpeichart()
                }


                else {
                    Toast.makeText(requireContext(), "Please FullFill The Data ", Toast.LENGTH_LONG).show()
                }
            }


        }






        Prefs.Builder()
                .setContext(requireContext())
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(requireActivity().packageName)
                .setUseDefaultSharedPreference(true)
                .build()





         Prefs.putString("stateofapp","cal_exp")



        return binding.root

    }

    private fun loadexpense() {
        if(Prefs.getString(dated + ",nameofexp", "").equals("")){
            Toast.makeText(requireContext(), "No Expenses Added", Toast.LENGTH_LONG).show()
        }
        else {
        binding.dateCalendra.setText(dated)
            //amount - amount
            // name -> nameofexp
            // des -> des
            //exp_typ
            //date
            //exp_image
            binding.rvExp1.adapter = null
            val names_exp : Array<String> =   Prefs.getString(dated + ",nameofexp", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
            val des : Array<String> =   Prefs.getString(dated + ",des", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
            val exp_type : Array<String> =   Prefs.getString(dated + ",exp_typ", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
            val time : Array<String> =   Prefs.getString(dated + ",date", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
            val exp_image : Array<String> =   Prefs.getString(dated + ",exp_image", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
            val amount : Array<String> =   Prefs.getString(dated + ",amount", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()




            var Adapter = Adapter_Expense_loader(
                    amount,
                    names_exp,
                    des,
                    exp_type,
                    exp_image,
                    time,
                    requireContext()
            )
          binding.rvExp1.layoutManager = LinearLayoutManager(requireContext())
        binding.rvExp1.adapter = Adapter

            val amount_a : Array<String?> =   Prefs.getString(dated + ",amount", "").split("[,]".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
            var total_spend  = 0
            for(i in 0 ..  amount_a.size-1){
                total_spend = amount_a[i].toString().toInt() + total_spend
            }
          binding.totalSpendDay.setText(total_spend.toString())

        }
    }

    private fun loadpeichart() {
        var shoping_amount = 0
        var home_amount = 0
        var medicine_amount = 0
        var res_amount = 0
        var education_amount = 0
        var other = 0

        val amounts : Array<String?> =   Prefs.getString(dated + ",amount", "").split("[,]".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
        val exp_types : Array<String?> =   Prefs.getString(dated + ",exp_typ", "").split("[,]".toRegex()).dropLastWhile { it.isEmpty() }
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

            binding.piechart1.clearChart()
            binding.piechart1.addPieSlice(
                    PieModel(
                            "Shopping",
                            shoping_amount.toFloat(),
                            Color.parseColor("#3C99EA")
                    )
            )
            binding.piechart1.addPieSlice(
                    PieModel(
                            "Home",
                            home_amount.toFloat(),
                            Color.parseColor("#24E40F")
                    )
            )
            binding.piechart1.addPieSlice(
                    PieModel(
                            "Medicine",
                            medicine_amount.toFloat(),
                            Color.parseColor("#FA3A3A")
                    )
            )
            binding.piechart1.addPieSlice(
                    PieModel(
                            "Restaurant",
                            res_amount.toFloat(),
                            Color.parseColor("#EF711C")
                    )
            )
            binding.piechart1.addPieSlice(
                    PieModel(
                            "Education", education_amount.toFloat(), Color.parseColor(
                            "#8351F6"
                    )
                    )
            )
            binding.piechart1.addPieSlice(PieModel("Others", other.toFloat(), Color.parseColor("#888888")))
            binding.piechart1.startAnimation()
    } }


    }