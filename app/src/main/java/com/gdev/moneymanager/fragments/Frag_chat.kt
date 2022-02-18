package com.gdev.moneymanager.fragments

import android.Manifest
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.print.PDFPrint.OnPDFPrintListener
import android.telephony.SmsManager
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
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
import com.tejpratapsingh.pdfcreator.utils.FileManager
import com.tejpratapsingh.pdfcreator.utils.PDFUtil
import java.io.File
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            var view_al: View = layoutInflater.inflate(R.layout.sending_sms, null)
            bb.setView(view_al)
            dialog = bb.create()
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
            val text_name: TextView = view_al.findViewById(R.id.text_msg_name)
            text_name.setText("Sending Message To $name")
            Handler().postDelayed(Runnable {
                val intent = Intent(context, requireActivity().javaClass)
                val pi = PendingIntent.getActivity(context, 0, intent, 0)
                val sms: SmsManager = SmsManager.getDefault()


                if (binding.textTotalAmountChjat.text.toString().toInt() < 0) {
                    sms.sendTextMessage(
                        Prefs.getString("current_chat_contact"),
                        null,
                        "Hii," + name + " I Have Created A Ledger In Case Your My Credit is " + binding.textTotalAmountChjat.text.toString() + "\n Digital Ledger By Money Manager App  ",
                        pi,
                        null
                    )
                    Toast.makeText(context, "Message Sent To $name", Toast.LENGTH_SHORT).show()
                    Toast.makeText(
                        context,
                        Prefs.getString("current_chat_contact"),
                        Toast.LENGTH_SHORT
                    ).show()
                    dialog.dismiss()
                }

                if (binding.textTotalAmountChjat.text.toString().toInt() > 0) {
                    sms.sendTextMessage(
                        Prefs.getString("current_chat_contact"),
                        null,
                        "Hii," + name + " I Have Created A Ledger In Case Your Debt is " + binding.textTotalAmountChjat.text.toString() + "\n Digital Ledger By Money Manager App  ",
                        pi,
                        null
                    )
                    Toast.makeText(context, "Message Sent To $name", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(context, "No Need TO Send Message To $name", Toast.LENGTH_SHORT)
                        .show()
                    dialog.dismiss()
                }


            }, 5000)

        }
        binding.cardAddChat.setOnClickListener {

            val shower_1 = AlertDialog.Builder(requireContext())
            var add_view: View = layoutInflater.inflate(R.layout.dialog_add_person_chat, null)
            radio1 = add_view.findViewById(R.id.radio1) as RadioButton
            radio2 = add_view.findViewById(R.id.radio2) as RadioButton
            shower_1.setView(add_view)
            card_show_add = shower_1.create()
            card_show_add.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            card_show_add.show()
            radio1.setOnClickListener {
                if (radio2.isChecked) {
                    radio2.toggle()
                } else {
                    check = "start"
                }
            }


            radio2.setOnClickListener {
                if (radio1.isChecked) {
                    radio1.toggle()
                } else {
                    check = "end"
                }
            }

            var button_submit: LinearLayout = add_view.findViewById(R.id.linear_add_chat_button) as LinearLayout
            var editText: EditText = add_view.findViewById(R.id.Edit_amount_chat) as EditText
            var text_from: TextView = add_view.findViewById(R.id.From_name_chat) as TextView
            var text_to: TextView = add_view.findViewById(R.id.To_name_chat) as TextView

            text_from.setText("From " + name)
            text_to.setText("To  " + name)


            button_submit.setOnClickListener {

                if (editText.text.isEmpty() || check.equals("")) {
                    Toast.makeText(
                        requireContext(),
                        "PLS INPUT AND SELECT CHECK BOX",
                        Toast.LENGTH_LONG
                    ).show()
                } else {


                    val dateFormat: DateFormat = SimpleDateFormat("hh:mm a")
                    val formattedDate = dateFormat.format(Date()).toString()
                    val c = Calendar.getInstance().time
                    println("Current time => $c")
                    val df1 = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
                    val formattedDate1 = df1.format(c)


                    var amount = editText.text.toString().replace(",", "")
                    if (check.equals("start")) {
                        var i = -amount.toInt()
                        amount = i.toString()
                    }



                    if (Prefs.getString("chat_" + name + "_amount", "").equals("")) {
                        Prefs.putString("chat_" + name + "_amount", amount)
                        Prefs.putString("chat_" + name + "_side", check)

                        Prefs.putString("chat_" + name + "_time", formattedDate)
                        Prefs.putString("chat_" + name + "_date", formattedDate1)
                        card_show_add.dismiss()
                        loaddata()
                    } else {
                        Prefs.putString(
                            "chat_" + name + "_amount", Prefs.getString(
                                "chat_" + name + "_amount",
                                ""
                            ) + "," + amount
                        )
                        Prefs.putString(
                            "chat_" + name + "_side", Prefs.getString(
                                "chat_" + name + "_side",
                                ""
                            ) + "," + check
                        )
                        Prefs.putString(
                            "chat_" + name + "_time", Prefs.getString(
                                "chat_" + name + "_time",
                                ""
                            ) + "," + formattedDate
                        )
                        Prefs.putString(
                            "chat_" + name + "_date", Prefs.getString(
                                "chat_" + name + "_date",
                                ""
                            ) + "," + formattedDate1
                        )

                        card_show_add.dismiss()
                        loaddata()
                    }


                }
            }


        }


        return binding.root
    }

    private fun createpdf() {
        val bb = AlertDialog.Builder(requireContext())
        var view_al: View = layoutInflater.inflate(R.layout.sending_sms, null)
        bb.setView(view_al)
        dialog = bb.create()
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val text_name: TextView = view_al.findViewById(R.id.text_msg_name)
        text_name.setText("Creating PDF Digital Shareable Ledger")
        val amount : Array<String> =   Prefs.getString("chat_" + name + "_amount", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
     val nameofto =    Prefs.getString("name", "")
      val number =   Prefs.getString("current_chat_contact")
       val time : Array<String> =   Prefs.getString("chat_" + name + "_time", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
        val date : Array<String> =   Prefs.getString("chat_" + name + "_date", "").split("[,]".toRegex()).dropLastWhile{ it.isEmpty() }.toTypedArray()
           var backcolor = ""
        var drbr = ""
        var tables = ""
        var amountdueoradd = binding.textTotalAmountChjat.text.toString()
        val c = Calendar.getInstance().time
        println("Current time => $c")
        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedDate = df.format(c)

        for(i in amount.indices) {
            if(amount[i].toInt() < 0  ){
                backcolor = "#F6B6A7"
                drbr = "CR"
            }
            if(amount[i].toInt() > 0  ){
                backcolor = "#9BF8C1"
                drbr = "DR"
            }


             tables = "<tr> " +
                    "\t\t\t\t\t\t<td><a class=\"cut\">-</a><span contenteditable>" + date[i] + " </span></td>\n" +
                    "\t\t\t\t\t\t\n" +
                    "\t\t\t\t\t\t<td><span data-prefix></span><span contenteditable>" + time[i] + "</span></td>\n" +
                    "\t\t\t\t\t\t<td style = \" background : $backcolor\" ; ><span contenteditable>" + amount[i] + "</span></td>\n" +
                    "\t\t\t\t\t\t<td ><span data-prefix>\$</span><span>$drbr</span></td>\n" +
                    "\t\t\t\t\t</tr> " + tables
        }



        val savedPDFFile: File = FileManager.getInstance().createTempFile(
            requireContext(),
            "pdf",
            false

        )

        PDFUtil.generatePDFFromHTML(requireContext(), savedPDFFile, """    
<html>
	<head>
    
		<meta charset="utf-8">
		<title>Money Manager</title>
    <style> *
{
	border: 0;
	box-sizing: content-box;
	color: inherit;
	font-family: inherit;
	font-size: inherit;
	font-style: inherit;
	font-weight: inherit;
	line-height: inherit;
	list-style: none;
	margin: 0;
	padding: 0;
	text-decoration: none;
	vertical-align: top;
}

/* content editable */

*[contenteditable] { border-radius: 0.25em; min-width: 1em; outline: 0; }

*[contenteditable] { cursor: pointer; }

*[contenteditable]:hover, *[contenteditable]:focus, td:hover *[contenteditable], td:focus *[contenteditable], img.hover { background: #DEF; box-shadow: 0 0 1em 0.5em #DEF; }

span[contenteditable] { display: inline-block; }

/* heading */

h1 { font: bold 100% sans-serif; letter-spacing: 0.5em; text-align: center; text-transform: uppercase; }

/* table */

table { font-size: 75%; table-layout: fixed; width: 100%; }
table { border-collapse: separate; border-spacing: 2px; }
th, td { border-width: 1px; padding: 0.5em; position: relative; text-align: left; }
th, td { border-radius: 0.25em; border-style: solid; }
th { background: #EEE; border-color: #BBB; }
td { border-color: #DDD;  }
tbody:before {
    content:"@";
    display:block;
    line-height:10px;
    text-indent:-99999px;
}

/* page */

html { font: 16px/1 'Open Sans', sans-serif; overflow: auto; padding: 0.5in; }
html { background: #999; cursor: default; }

body { box-sizing: border-box; height: 11in; margin: 0 auto; overflow: hidden; padding: 0.5in; width: 8.5in; }
body { background: #FFF; border-radius: 1px; box-shadow: 0 0 1in -0.25in rgba(0, 0, 0, 0.5); }

/* header */
header h1 {height : 50px; background: #2B88C6; border-radius: 0.25em; color: #FFF; margin: 0 0 1em; padding: 0.5em 0; font-size: 150%;   }

header { margin: 0 0 3em; }
.header:after { clear: both; content: ""; display: table; }
header address { float: left; font-size: 75%; font-style: normal; line-height: 1.25; margin: 0 1em 1em 0; }
header address p { margin: 0 0 0.25em; }
header span, header img { display: block; float: right; }
header span { margin: 0 0 1em 1em; max-height: 25%; max-width: 60%; position: relative; }
header img { max-height: 100%; max-width: 100%; }
header input { cursor: pointer; -ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)"; height: 100%; left: 0; opacity: 0; position: absolute; top: 0; width: 100%; }

/* article */

article, article address, table.meta, table.inventory { margin: 0 0 3em; }
article:after { clear: both; content: ""; display: table; }
article h1 { clip: rect(0 0 0 0); position: absolute; }

article address { float: left; font-size: 125%; font-weight: bold; }

/* table meta & balance */

table.meta, table.balance { float: right; width: 36%; }
table.meta:after, table.balance:after { clear: both; content: ""; display: table; }

/* table meta */

table.meta th { width: 40%; }
table.meta td { width: 60%; }

/* table items */

table.inventory { clear: both; width: 100%; }
table.inventory th { font-weight: bold; text-align: center; }

table.inventory td:nth-child(1) { width: 26%; }
table.inventory td:nth-child(2) { width: 38%; }
table.inventory td:nth-child(3) { text-align: right; width: 12%; }
table.inventory td:nth-child(4) { text-align: right; width: 12%; }
table.inventory td:nth-child(5) { text-align: right; width: 12%; }

/* table balance */

table.balance th, table.balance td { width: 50%; }
table.balance td { text-align: right; }

/* aside */

aside h1 { border: none; border-width: 0 0 1px; margin: 0 0 1em; }
aside h1 { border-color: #990; border-bottom-style: solid; }

/* javascript */

.add, .cut
{
	border-width: 1px;
	display: block;
	font-size: .8rem;
	padding: 0.25em 0.5em;	
	float: left;
	text-align: center;
	width: 0.6em;
}

.add, .cut
{
	background: #9AF;
	box-shadow: 0 1px 2px rgba(0,0,0,0.2);
	background-image: -moz-linear-gradient(#00ADEE 5%, #0078A5 100%);
	background-image: -webkit-linear-gradient(#00ADEE 5%, #0078A5 100%);
	border-radius: 0.5em;
	border-color: #0076A3;
	color: #FFF;
	cursor: pointer;
	font-weight: bold;
	text-shadow: 0 -1px 2px rgba(0,0,0,0.333);
}

.add { margin: -2.5em 0 0; }

.add:hover { background: #00ADE; }

.cut { opacity: 0; position: absolute; top: 0; left: -1.5em; }
.cut { -webkit-transition: opacity 100ms ease-in; }

tr:hover .cut { opacity: 1; }

@media print {
	* { -webkit-print-color-adjust: exact; }
	html { background: none; padding: 0; }
	body { box-shadow: none; margin: 0; }
	span:empty { display: none; }
	.add, .cut { display: none; }
}

@page { margin: 0; }</style>

		
	</head>
	<body>
		<header>
			<h1>Money Manager</h1>
		
			<Img  height = 150 src = "https://i.ibb.co/w7JQSsF/icon-app.png"> <Img/>
		</header>
		<article>
			<h1>Recipient</h1>
			<address contenteditable>
				<p style =" color : #079F45" >$name To $nameofto <br> <br> Number :  $number</p>
			</address>
			<table class="meta">
				
				<tr>
					<th><span contenteditable>Date</span></th>
					<td><span contenteditable>$formattedDate</span></td>
				</tr>
				<tr>
					<th><span contenteditable>Amount Due / Add</span></th>
					<td><span id="prefix" contenteditable>$amountdueoradd </span></td>
				</tr>
			</table>
			<table class="inventory">
				<thead>
					<tr>
						<th><span contenteditable>Date</span></th>
					
						<th><span contenteditable>Time</span></th>
						<th><span contenteditable>Amount</span></th>
						<th><span contenteditable>Dr/Cr</span></th>
					</tr>
				</thead>
				<tbody>
				   	$tables
       
				</tbody>
			</table>
			
			
		</article>
		<aside>
			<h1><span contenteditable>Additional Notes</span></h1>
			<div contenteditable>
				<p> This is Not For Business Purpose Regards Money Manager By Girijesh </p>
			</div>
		</aside>
	</body>
</html>   """, object : OnPDFPrintListener {
            override fun onSuccess(file: File?) {
                dialog.dismiss()
                 share(file)
            }

            override fun onError(exception: Exception) {
                Toast.makeText(context, exception.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        }

    private fun share(file: File?) {
try {
   var b : StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
    StrictMode.setVmPolicy(b.build())
    file?.let {
        FileProvider.getUriForFile(
            requireContext(),
        "com.gdev.moneymanager.provider",
            it
        )
    };
    var uri : Uri = Uri.fromFile(file)

    val intent = ShareCompat.IntentBuilder.from(requireActivity())
        .setType("application/pdf")
        .setStream(uri)
        .setChooserTitle("Choose bar")
        .createChooserIntent()
        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    requireContext().startActivity(intent)
}
catch (e : Exception)
{
    Toast.makeText(requireContext(),e.message,Toast.LENGTH_SHORT).show()

}
    }


    private fun checkpermission() {
        Dexter.withContext(context)
                    .withPermissions(
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) { /* ... */
                    if (report.areAllPermissionsGranted()) {

                    } else {
                        Toast.makeText(context, "Pls Give Permission ", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) { /* ... */

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
        var adapter : Adapter_chat_per = Adapter_chat_per(
            amount,
            side,
            requireContext(),
            x,
            time,
            y,
            date
        )
        var recyclerView = binding.rvChat
         recyclerView.apply {
             layoutManager = LinearLayoutManager(context).apply {
                 stackFromEnd = true
                 reverseLayout = false
             }
         }


        recyclerView.adapter = adapter
    }   }


