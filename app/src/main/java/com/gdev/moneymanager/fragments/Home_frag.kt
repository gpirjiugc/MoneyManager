package com.gdev.moneymanager.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.LinkAddress
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.gdev.moneymanager.*
import com.gdev.moneymanager.Activity.Balance_Check
import com.gdev.moneymanager.Activity.HomeActivty
import com.gdev.moneymanager.Activity.Monthly_expenses
import com.gdev.moneymanager.Activity.Server_Check
import com.gdev.moneymanager.ViewModel.ViewModelHome
import com.gdev.moneymanager.databinding.FragmentHomeFragBinding
import com.nitish.typewriterview.TypeWriterView
import com.pixplicity.easyprefs.library.Prefs
import kotlin.time.seconds

class home_frag : Fragment() {


    lateinit var imageView_profile: ImageView
    lateinit var binding : FragmentHomeFragBinding
    lateinit var text_bat : TextView
    lateinit var card_ledger : CardView
    lateinit var text_name : TextView
    lateinit var viewmodelhome : ViewModelHome
    lateinit var linear_extra_home : LinearLayout
    lateinit var text_spend_today : TextView
    lateinit var bal : LinearLayout
    lateinit var linear_bal:LinearLayout
    lateinit var aa : AlertDialog
    lateinit var  monthly_lin : LinearLayout
     lateinit var add_shower : AlertDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Prefs.Builder()
            .setContext(requireActivity())
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(requireActivity().packageName)
            .setUseDefaultSharedPreference(true)
            .build()



        binding = FragmentHomeFragBinding.inflate(layoutInflater)
        bal = binding.linearBankBalanc
        text_bat = binding.textHomeMoney
        text_name = binding.textHomeName
        text_spend_today = binding.totalExp
         card_ledger = binding.cardLedger
        linear_bal = binding.LinearAddBal
        linear_extra_home = binding.linearExtraHome
       monthly_lin = binding.MonthlyAnay


                basictask()
      //  tutorialshower()






        viewmodelhome = ViewModelProvider(this).get(ViewModelHome::class.java)
        viewmodelhome.bal.observe(viewLifecycleOwner, Observer {
            text_bat.setText(it)
        })
        viewmodelhome.total_exp.observe(viewLifecycleOwner, Observer {
            text_spend_today.setText(it)
        })



        






        val amount_a : Array<String?> =   Prefs.getString("amount", "").split("[,]".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        var total_spend : Int = 0
        for(i in 0 ..  amount_a.size-1){
            total_spend = amount_a[i].toString().toInt() + total_spend
        }

        viewmodelhome.set_bal(Prefs.getString("balance", ""),total_spend.toString())
        text_name.text = Prefs.getString("name", "")





        return binding.root
    }

    private fun tutorialshower() {
        val shower_1 = AlertDialog.Builder(requireContext())
        var view_shower :View = layoutInflater.inflate(R.layout.dialog_tut_home_viewer, null)

        shower_1.setView(view_shower)

        add_shower = shower_1.create()
        add_shower.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val window: Window? = add_shower.getWindow()
        val wlp: WindowManager.LayoutParams = window!!.getAttributes()

        val typeWriterView: TypeWriterView = view_shower.findViewById(R.id.text_home_typr_1) as TypeWriterView
        typeWriterView.setCharacterDelay(70)
        typeWriterView.animateText("Hello Gays , Im Girijesh Android Developer ,In This App  you Will Get Everything That You Want ,Let Me Introduce My App ")


        wlp.gravity = Gravity.CENTER
        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
        window.setAttributes(wlp)
        add_shower.show()

        Handler().postDelayed(Runnable {
            add_shower.dismiss()

            val shower_1 = AlertDialog.Builder(requireContext())
            var view_shower :View = layoutInflater.inflate(R.layout.dialog_tut_home_viewer1, null)
            var linearbutton : LinearLayout = view_shower.findViewById(R.id.linear_button_tut_home1)

            shower_1.setView(view_shower)

            add_shower = shower_1.create()
            add_shower.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val window: Window? = add_shower.getWindow()
            val wlp: WindowManager.LayoutParams = window!!.getAttributes()



            wlp.gravity = Gravity.TOP
            wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
            window.setAttributes(wlp)

            add_shower.show()

            linearbutton.setOnClickListener {
                add_shower.dismiss()

                val shower_1 = AlertDialog.Builder(requireContext())
                var view_shower :View = layoutInflater.inflate(R.layout.dialog_tut_home_viewer2, null)
                var linearbutton : LinearLayout = view_shower.findViewById(R.id.linear_button_tut_home2)

                shower_1.setView(view_shower)

                add_shower = shower_1.create()
                add_shower.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                val window: Window? = add_shower.getWindow()
                val wlp: WindowManager.LayoutParams = window!!.getAttributes()



                wlp.gravity = Gravity.CENTER
                wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
                window.setAttributes(wlp)
                add_shower.show()
                linearbutton.setOnClickListener {
                    add_shower.dismiss()

                    val shower_1 = AlertDialog.Builder(requireContext())
                    var view_shower: View =
                        layoutInflater.inflate(R.layout.dialog_tut_home_viewer3, null)
                    var linearbutton: LinearLayout =
                        view_shower.findViewById(R.id.linear_button_tut_home3)

                    shower_1.setView(view_shower)

                    add_shower = shower_1.create()
                    add_shower.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    val window: Window? = add_shower.getWindow()
                    val wlp: WindowManager.LayoutParams = window!!.getAttributes()



                    wlp.gravity = Gravity.CENTER
                    wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
                    window.setAttributes(wlp)
                    add_shower.show()
                    linearbutton.setOnClickListener {
                        add_shower.dismiss()

                        val shower_1 = AlertDialog.Builder(requireContext())
                        var view_shower: View =
                            layoutInflater.inflate(R.layout.dialog_tut_home_viewer4, null)
                        var linearbutton: LinearLayout =
                            view_shower.findViewById(R.id.linear_button_tut_home4)

                        shower_1.setView(view_shower)

                        add_shower = shower_1.create()
                        add_shower.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                        val window: Window? = add_shower.getWindow()
                        val wlp: WindowManager.LayoutParams = window!!.getAttributes()



                        wlp.gravity = Gravity.CENTER
                        wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
                        window.setAttributes(wlp)
                        add_shower.show()
                        linearbutton.setOnClickListener {
                            add_shower.dismiss()

                            val shower_1 = AlertDialog.Builder(requireContext())
                            var view_shower: View =
                                layoutInflater.inflate(R.layout.dialog_tut_home_viewer5, null)
                            var linearbutton: LinearLayout =
                                view_shower.findViewById(R.id.linear_button_tut_home5)

                            shower_1.setView(view_shower)

                            add_shower = shower_1.create()
                            add_shower.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                            val window: Window? = add_shower.getWindow()
                            val wlp: WindowManager.LayoutParams = window!!.getAttributes()



                            wlp.gravity = Gravity.CENTER
                            wlp.flags = wlp.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()
                            window.setAttributes(wlp)
                            add_shower.show()
                            linearbutton.setOnClickListener { add_shower.dismiss() }
                        }
                    }
                }


            }

        },18000)


    }

    private fun basictask() {


        monthly_lin.setOnClickListener {
            startActivity(Intent(requireContext(),Monthly_expenses::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }

        linear_bal.setOnClickListener{
            val bb = AlertDialog.Builder(requireContext())
            var view_al:View = layoutInflater.inflate(R.layout.dialog_add_balance, null)
            bb.setView(view_al)

            aa = bb.create()
            aa.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            aa.show()

            var editText : EditText = view_al.findViewById(R.id.bal_add_dialog_edittext) as EditText
            var linear_button : LinearLayout = view_al.findViewById(R.id.linear_add_exp_dialog_button_bal) as LinearLayout
            linear_button.setOnClickListener {
                if(editText.text.toString().isEmpty()){
                    Toast.makeText(requireContext(),"Pls Add Balance ",Toast.LENGTH_LONG).show()
                }
                else {
                    var string = editText.text.toString().replace(",","")

                    var newbal: Int = Prefs.getString("balance","").toInt() + string.toString().toInt()
                    Prefs.putString("balance",newbal.toString())
                    aa.dismiss()
                }

            }

        }


        var displaymatrix = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displaymatrix)
        var x  = displaymatrix.widthPixels

        if(x >=490 )
        {
            var params = linear_extra_home.layoutParams
            params.height = 200
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            linear_extra_home.requestLayout()



        }
        else {


            var params = linear_extra_home.layoutParams
            params.height = 0

            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            linear_extra_home.requestLayout()

        }
        bal.setOnClickListener{
            startActivity( Intent(activity, com.gdev.moneymanager.Activity.Balance_Check::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
        card_ledger.setOnClickListener {
            HomeActivty.dochangeindex(2)
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame1,frag_ledger()).commit()


        }

        var server_button = binding.linearServerButton
        server_button.setOnClickListener{
            startActivity( Intent(activity, Server_Check::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
        imageView_profile = binding.imageHomeProfile
        var LinearLayout = binding.LinearExpenesHomeFrag
        LinearLayout.setOnClickListener {
              HomeActivty.dochangeindex(1)
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame1, frag_expenses()).commit()
        }
        Glide.with(requireActivity()).load(Prefs.getString("image")).into(imageView_profile)
        requireActivity().window.statusBarColor = resources.getColor(R.color.mycolorback)

    }


}