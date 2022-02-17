package com.gdev.moneymanager.fragments

import android.Manifest
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.telephony.SmsManager
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.gdev.moneymanager.Adapter.Adapter_chat_per
import com.gdev.moneymanager.R
import com.gdev.moneymanager.databinding.FragmentFragChatBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.pixplicity.easyprefs.library.Prefs
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class Frag_chat : Fragment() {

lateinit var card_show_add : AlertDialog
lateinit var radio1 : RadioButton
lateinit var radio2 : RadioButton
var check = ""
   lateinit var binding : FragmentFragChatBinding
   lateinit var dialog: AlertDialog
    var name = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       binding = FragmentFragChatBinding.inflate(layoutInflater)

        checkpermission()

        Prefs.Builder()
                .setContext(requireContext())
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(requireActivity().packageName)
                .setUseDefaultSharedPreference(true)
                .build()
        Prefs.putString("stateofapp", "frag_chat")

        name = Prefs.getString("current_chat_name", "")
        binding.chatUserName.setText(name)
        binding.chatNickname.setText(Prefs.getString("current_chat_nick_name", ""))
        binding.personChatNameDown.setText("Total Amount (Pay or Have) To " + binding.chatUserName.text)

        Glide.with(this).load(Prefs.getString("current_chat_image", "")).into(binding.imageProfileContactChat)



        loaddata()
        binding.linearLedgerPdf.setOnClickListener { createpdf() }
       binding.messageForMoney.setOnClickListener {

           val bb = AlertDialog.Builder(requireContext())
           var view_al:View = layoutInflater.inflate(R.layout.sending_sms, null)
           bb.setView(view_al)
           dialog = bb.create()
           dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
           dialog.show()
           val text_name : TextView = view_al.findViewById(R.id.text_msg_name)
           text_name.setText("Sending Message To $name")
           Handler().postDelayed(Runnable {
               val intent = Intent(context, requireActivity().javaClass)
               val pi = PendingIntent.getActivity(context, 0, intent, 0)
               val sms: SmsManager = SmsManager.getDefault()


               if (binding.textTotalAmountChjat.text.toString().toInt() < 0) {
                   sms.sendTextMessage(Prefs.getString("current_chat_contact"), null, "Hii," + name + " I Have Created A Ledger In Case Your My Credit is " + binding.textTotalAmountChjat.text.toString() + "\n Digital Ledger By Money Manager App  ", pi, null)
                   Toast.makeText(context, "Message Sent To $name", Toast.LENGTH_SHORT).show()
                   Toast.makeText(context, Prefs.getString("current_chat_contact"), Toast.LENGTH_SHORT).show()
                   dialog.dismiss()
               }

               if (binding.textTotalAmountChjat.text.toString().toInt() > 0) {
                   sms.sendTextMessage(Prefs.getString("current_chat_contact"), null, "Hii," + name + " I Have Created A Ledger In Case Your Debt is " + binding.textTotalAmountChjat.text.toString() + "\n Digital Ledger By Money Manager App  ", pi, null)
                   Toast.makeText(context, "Message Sent To $name", Toast.LENGTH_SHORT).show()
                   dialog.dismiss()
               } else {
                   Toast.makeText(context, "No Need TO Send Message To $name", Toast.LENGTH_SHORT).show()
                   dialog.dismiss()
               }


           }, 5000)

       }


        binding.cardAddChat.setOnClickListener {

            val shower_1 = AlertDialog.Builder(requireContext())
            var add_view :View = layoutInflater.inflate(R.layout.dialog_add_person_chat, null)
            radio1 = add_view.findViewById(R.id.radio1) as RadioButton
            radio2 = add_view.findViewById(R.id.radio2) as RadioButton
            shower_1.setView(add_view)
            card_show_add = shower_1.create()
            card_show_add.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            card_show_add.show()
            radio1.setOnClickListener {
                if(radio2.isChecked){ radio2.toggle() }
                else { check ="start" }}


            radio2.setOnClickListener {
                if(radio1.isChecked){ radio1.toggle() }
                else { check ="end" }}

            var button_submit : LinearLayout = add_view.findViewById(R.id.linear_add_chat_button) as LinearLayout
            var editText : EditText = add_view.findViewById(R.id.Edit_amount_chat) as EditText
            var text_from : TextView = add_view.findViewById(R.id.From_name_chat) as TextView
            var text_to: TextView = add_view.findViewById(R.id.To_name_chat) as TextView

            text_from.setText("From " + name)
            text_to.setText("To  " + name)


            button_submit.setOnClickListener {

                if(editText.text.isEmpty()||check.equals("")){
                    Toast.makeText(requireContext(), "PLS INPUT AND SELECT CHECK BOX", Toast.LENGTH_LONG).show()
                }

                else {




                    val dateFormat: DateFormat = SimpleDateFormat("hh:mm a")
                    val formattedDate = dateFormat.format(Date()).toString()
                    val c = Calendar.getInstance().time
                    println("Current time => $c")
                    val df1 = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
                    val formattedDate1 = df1.format(c)


                    var amount = editText.text.toString().replace(",", "")
                    if(check.equals("start")){
                       var i = - amount.toInt()
                        amount = i.toString()
                    }



                    if(Prefs.getString("chat_" + name + "_amount", "").equals("")){
                        Prefs.putString("chat_" + name + "_amount", amount)
                        Prefs.putString("chat_" + name + "_side", check)

                        Prefs.putString("chat_" + name + "_time", formattedDate)
                        Prefs.putString("chat_" + name + "_date", formattedDate1)
                        card_show_add.dismiss()
                        loaddata()
                    }
                    else {
                        Prefs.putString("chat_" + name + "_amount", Prefs.getString("chat_" + name + "_amount", "") + "," + amount)
                        Prefs.putString("chat_" + name + "_side", Prefs.getString("chat_" + name + "_side", "") + "," + check)
                        Prefs.putString("chat_" + name + "_time", Prefs.getString("chat_" + name + "_time", "") + "," + formattedDate)
                        Prefs.putString("chat_" + name + "_date", Prefs.getString("chat_" + name + "_date", "") + "," + formattedDate1)

                        card_show_add.dismiss()
                        loaddata()
                    }


                } }





        }


        return binding.root
    }

    private fun createpdf() {
        val amount : Array<String> =   Prefs.getString("chat_" + name + "_amount", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()

        var bmp: Bitmap
        var scaledbmp: Bitmap
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon_app);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false)

        val pdfDocument = PdfDocument()

        val paint = Paint()
        var h =0
        val title = Paint()
        val mypageInfo : PageInfo

Toast.makeText(context,amount.size.toString() ,Toast.LENGTH_SHORT).show()

        mypageInfo   = PageInfo.Builder(1200,  amount.size * 150 + 100, 1).create()






        val myPage = pdfDocument.startPage(mypageInfo)


        val canvas: Canvas = myPage.canvas

        canvas.drawBitmap(scaledbmp,50F,60F,paint)



        // below line is sued for setting color
        // of our text inside our PDF file.

        // below line is sued for setting color
        title.setTextAlign(Paint.Align.LEFT)
        title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
        title.setColor(ContextCompat.getColor(requireContext(), R.color.mycolorback))
        title.setTextSize(45F)

        canvas.drawText("Money Manager App A Digital Ledger ", 100F, 80F, title)
        title.setTextSize(35F)
        title.setColor(ContextCompat.getColor(requireContext(), R.color.green))
        title.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC))

        canvas.drawText( "Name  :  $name ", 209F, title.textSize + 100F, title)

        canvas.drawText( "Number  : "+Prefs.getString("current_chat_contact"), 209F,  175F, title)




       val time : Array<String> =   Prefs.getString("chat_" + name + "_time", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
        val date : Array<String> =   Prefs.getString("chat_" + name + "_date", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()

             var maintextfollw = "      Date                              Time                    Amount    "
       paint.textSize = 30F
         var x = 200F
      var  y = 300F
        title.setTextAlign(Paint.Align.CENTER)
        for(i in amount.indices){
           if(i == 0){
               canvas.drawText( maintextfollw ,x,y,paint)
               y += paint.getTextSize() + 10
               y += paint.getTextSize();

               canvas.drawText(""+date[i] + "               " + time[i] + "                     " + amount[i], x, y, paint)
               y += paint.getTextSize();
           }
            else {
               y += paint.getTextSize();

               canvas.drawText(""+date[i] + "               " + time[i] + "                     " + amount[i], x, y, paint)

               y += paint.getTextSize();
           }
        }






        pdfDocument.finishPage(myPage)

        val file = File(Environment.getExternalStorageDirectory(), "$name DigitalLedger.pdf")

        try {

            pdfDocument.writeTo(FileOutputStream(file))


        } catch (e: IOException) {

            e.printStackTrace()
        }

        pdfDocument.close()
    }

    private fun checkpermission() {
        Dexter.withContext(context)
                    .withPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) { /* ... */
                        if (report.areAllPermissionsGranted()) {

                        } else {
                            Toast.makeText(context, "Pls Give Permission ", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) { /* ... */

                        token.continuePermissionRequest()
                    }
                }).check()

           }


    private fun loaddata() {
        val amount : Array<String> =   Prefs.getString("chat_" + name + "_amount", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
        val side : Array<String> =   Prefs.getString("chat_" + name + "_side", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
        val time : Array<String> =   Prefs.getString("chat_" + name + "_time", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
        val date : Array<String> =   Prefs.getString("chat_" + name + "_date", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
         var totalamount : Int = 0
        for(i in amount.indices) totalamount += amount[i].toInt()


        if(totalamount > 0){
            binding.textTotalAmountChjat.setTextColor(resources.getColor(R.color.green))
        }
        if(totalamount < 0){
            binding.textTotalAmountChjat.setTextColor(resources.getColor(R.color.red))
        }
         binding.textTotalAmountChjat.setText(totalamount.toString())
        var displaymatrix = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displaymatrix)
        var x  = displaymatrix.widthPixels
        var y = displaymatrix.heightPixels
        var adapter : Adapter_chat_per = Adapter_chat_per(amount, side, requireContext(), x, time, y, date)
        var recyclerView = binding.rvChat
         recyclerView.apply {
             layoutManager = LinearLayoutManager(context).apply {
                 stackFromEnd = true
                 reverseLayout = false
             }
         }


        recyclerView.adapter = adapter
    } }