package com.gdev.moneymanager.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContextWrapper
import android.content.Intent
import android.database.Cursor
import android.icu.lang.UProperty.NAME
import android.net.Uri
import android.os.Bundle
import android.provider.Contacts.People
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.gdev.moneymanager.R
import com.gdev.moneymanager.databinding.ActivitySelecterContactBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.pixplicity.easyprefs.library.Prefs


class Activity_Selecter_Contact : AppCompatActivity() {

    lateinit var aa : AlertDialog
    var id = ""
    var name = ""
    var number = ""

    lateinit var binding: ActivitySelecterContactBinding

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)




            if (resultCode == Activity.RESULT_OK) {



                var imageView  = binding.imageProfileContact
               var contactData : Uri = data!!.getData()!!;

                val cr = contentResolver
                val cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, "DISPLAY_NAME = '" + NAME.toString() + "'", null, null)
                var c  : Cursor = managedQuery(contactData, null, null, null, null);
                if (c.moveToFirst()) {
                    var name1 = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    val contactId: String = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID))

               var       phones : Cursor = cr.query(Phone.CONTENT_URI, null,
                       Phone.CONTACT_ID + " = " + contactId, null, null)!!;
                    while (phones.moveToNext()) {
                        number =    phones.getString(phones.getColumnIndex(Phone.NUMBER));
                    }

                   name = name1










                    val bb = AlertDialog.Builder(this)
                    val ch: View = layoutInflater.inflate(R.layout.dialog_selecter_profile, null)
                    bb.setView(ch)
                    aa = bb.create()


                    var imageView1 : ImageView = ch.findViewById(R.id.profiel_select_dialog_1) as ImageView
                    var imageView2 : ImageView = ch.findViewById(R.id.profiel_select_dialog_2) as ImageView
                    var imageView3 : ImageView = ch.findViewById(R.id.profiel_select_dialog_3) as ImageView
                    var imageView4 : ImageView = ch.findViewById(R.id.profiel_select_dialog_4) as ImageView
                    var imageView5 : ImageView = ch.findViewById(R.id.profiel_select_dialog_5) as ImageView
                    var imageView6 : ImageView = ch.findViewById(R.id.profiel_select_dialog_6) as ImageView
                    var imageView7 : ImageView = ch.findViewById(R.id.profiel_select_dialog_7) as ImageView
                    var imageView8 : ImageView = ch.findViewById(R.id.profiel_select_dialog_8) as ImageView
                    var imageView9 : ImageView = ch.findViewById(R.id.profiel_select_dialog_9) as ImageView
                    var imageView10 : ImageView = ch.findViewById(R.id.profiel_select_dialog_10) as ImageView
                    var imageView11 : ImageView = ch.findViewById(R.id.profiel_select_dialog_11) as ImageView
                    var imageView12 : ImageView = ch.findViewById(R.id.profiel_select_dialog_12) as ImageView



                        var displaymatrix = DisplayMetrics()
                        windowManager.defaultDisplay.getMetrics(displaymatrix)
                        var x  = displaymatrix.widthPixels / 100 * 75
                        var card_select_profile : CardView = ch.findViewById(R.id.card_selecter_profiile)
                        aa.show()
                        var params =card_select_profile.layoutParams
                        params.width = x
                        params.height = WindowManager.LayoutParams.WRAP_CONTENT
                        card_select_profile.requestLayout()


                    imageView1.setOnClickListener {
                        imageView.setImageDrawable(imageView1.drawable)
                        id = "https://i.ibb.co/BsX1RDr/icon-profile-1.png"
                        aa.dismiss()
                       closetheactivty()


                    }
                    imageView2.setOnClickListener {
                        imageView.setImageDrawable(imageView2.drawable)
                        id = " https://i.ibb.co/Trm3Dhk/icon-profile-2.png"
                        aa.dismiss()
                        closetheactivty()
                    }
                    imageView3.setOnClickListener {
                        imageView.setImageDrawable(imageView3.drawable)
                        id = "https://i.ibb.co/qd0QgLD/icon-profile-3.png"
                        aa.dismiss()
                        closetheactivty()
                    }
                    imageView4.setOnClickListener {
                        imageView.setImageDrawable(imageView4.drawable)
                        id = "https://i.ibb.co/Ltqs90V/icon-profile-4.png"
                        aa.dismiss()
                        closetheactivty()
                    }
                    imageView5.setOnClickListener {
                        imageView.setImageDrawable(imageView5.drawable)
                        id = "https://i.ibb.co/T2kzg9v/icon-profile-5.png"
                        closetheactivty()
                    }
                    imageView6.setOnClickListener {
                        imageView.setImageDrawable(imageView6.drawable)
                        id = "https://i.ibb.co/6DTLpyy/icon-profile-6.png"
                        aa.dismiss()
                        closetheactivty()
                    }
                    imageView7.setOnClickListener {
                        imageView.setImageDrawable(imageView7.drawable)
                        id = "https://i.ibb.co/17YncVw/icon-profile-7.png"
                        aa.dismiss()
                        closetheactivty()
                    }
                    imageView8.setOnClickListener {
                        imageView.setImageDrawable(imageView8.drawable)
                        id = "https://i.ibb.co/grwdbFL/icon-profile-8.png"
                        aa.dismiss()
                        closetheactivty()
                    }
                    imageView9.setOnClickListener {
                        imageView.setImageDrawable(imageView9.drawable)
                        id = "https://i.ibb.co/n0CQ6N4/icon-profile-9.png"
                        aa.dismiss()
                        closetheactivty()
                    }
                    imageView10.setOnClickListener {
                        imageView.setImageDrawable(imageView10.drawable)
                        id = "https://i.ibb.co/54T09DR/icon-profile-10.png"

                        aa.dismiss()
                        closetheactivty()
                    }
                    imageView11.setOnClickListener {
                        imageView.setImageDrawable(imageView11.drawable)
                        id = "https://i.ibb.co/NC7jXQz/icon-profile-11.png"
                        aa.dismiss()
                        closetheactivty()
                    }
                    imageView12.setOnClickListener {
                        imageView.setImageDrawable(imageView12.drawable)
                        id = "https://i.ibb.co/Hh4hrTJ/icon-profile-12.png"
                        aa.dismiss()
                        closetheactivty()
                    }


                }
            }

        }

    private fun closetheactivty() {
        if(Prefs.getString("Contact_Name", "").equals("")){
            Prefs.putString("Contact_Name", name)
            Prefs.putString("Nick_Name", name)
            Prefs.putString("Contact_Number", number)
            Prefs.putString("Contact_Profile", id)
            startActivity(Intent(this, HomeActivty::class.java).putExtra("rcv", "per"))
            finish()
        }
        else {
            Prefs.putString("Contact_Name", Prefs.getString("Contact_Name", "") + "," + name)
            Prefs.putString("Nick_Name", Prefs.getString("Nick_Name", "") + "," + name)
            Prefs.putString("Contact_Number", Prefs.getString("Contact_Number", "") + "," + number)
            Prefs.putString("Contact_Profile", Prefs.getString("Contact_Profile", "") + "," + id)
            startActivity(Intent(this, HomeActivty::class.java).putExtra("rcv", "per"))
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivitySelecterContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()






        var imageView  = binding.imageProfileContact
        binding.fecthContact.setOnClickListener {


            Dexter.withContext(this)
                    .withPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS).withListener(object : MultiplePermissionsListener {
                        override fun onPermissionsChecked(report: MultiplePermissionsReport) { /* ... */
                            if (report.areAllPermissionsGranted()) {
                                val intent = Intent(Intent.ACTION_PICK, People.CONTENT_URI)
                                startActivityForResult(intent, 1)
                            } else {
                                val intent = Intent(Intent.ACTION_PICK, People.CONTENT_URI)
                                startActivityForResult(intent, 1)
                            }
                        }

                        override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) { /* ... */

                            token.continuePermissionRequest()
                        }
                    }).check()

        }



        val bb = AlertDialog.Builder(this)
        val ch: View = layoutInflater.inflate(R.layout.dialog_selecter_profile, null)
        bb.setView(ch)
        aa = bb.create()


        var imageView1 : ImageView = ch.findViewById(R.id.profiel_select_dialog_1) as ImageView
        var imageView2 : ImageView = ch.findViewById(R.id.profiel_select_dialog_2) as ImageView
        var imageView3 : ImageView = ch.findViewById(R.id.profiel_select_dialog_3) as ImageView
        var imageView4 : ImageView = ch.findViewById(R.id.profiel_select_dialog_4) as ImageView
        var imageView5 : ImageView = ch.findViewById(R.id.profiel_select_dialog_5) as ImageView
        var imageView6 : ImageView = ch.findViewById(R.id.profiel_select_dialog_6) as ImageView
        var imageView7 : ImageView = ch.findViewById(R.id.profiel_select_dialog_7) as ImageView
        var imageView8 : ImageView = ch.findViewById(R.id.profiel_select_dialog_8) as ImageView
        var imageView9 : ImageView = ch.findViewById(R.id.profiel_select_dialog_9) as ImageView
        var imageView10 : ImageView = ch.findViewById(R.id.profiel_select_dialog_10) as ImageView
        var imageView11 : ImageView = ch.findViewById(R.id.profiel_select_dialog_11) as ImageView
        var imageView12 : ImageView = ch.findViewById(R.id.profiel_select_dialog_12) as ImageView


        binding.SelectImageAddPer.setOnClickListener {
            var displaymatrix = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displaymatrix)
            var x  = displaymatrix.widthPixels / 100 * 75
            var card_select_profile : CardView = ch.findViewById(R.id.card_selecter_profiile)
            aa.show()
            var params =card_select_profile.layoutParams
            params.width = x
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            card_select_profile.requestLayout()
        }

        imageView1.setOnClickListener {
            imageView.setImageDrawable(imageView1.drawable)
            id = "https://i.ibb.co/BsX1RDr/icon-profile-1.png"
            aa.dismiss()


        }
        imageView2.setOnClickListener {
            imageView.setImageDrawable(imageView2.drawable)
            id = " https://i.ibb.co/Trm3Dhk/icon-profile-2.png"
            aa.dismiss()
        }
        imageView3.setOnClickListener {
            imageView.setImageDrawable(imageView3.drawable)
            id = "https://i.ibb.co/qd0QgLD/icon-profile-3.png"
            aa.dismiss()
        }
        imageView4.setOnClickListener {
            imageView.setImageDrawable(imageView4.drawable)
            id = "https://i.ibb.co/Ltqs90V/icon-profile-4.png"
            aa.dismiss()
        }
        imageView5.setOnClickListener {
            imageView.setImageDrawable(imageView5.drawable)
            id = "https://i.ibb.co/T2kzg9v/icon-profile-5.png"
        }
        imageView6.setOnClickListener {
            imageView.setImageDrawable(imageView6.drawable)
            id = "https://i.ibb.co/6DTLpyy/icon-profile-6.png"
            aa.dismiss()
        }
        imageView7.setOnClickListener {
            imageView.setImageDrawable(imageView7.drawable)
            id = "https://i.ibb.co/17YncVw/icon-profile-7.png"
            aa.dismiss()
        }
        imageView8.setOnClickListener {
            imageView.setImageDrawable(imageView8.drawable)
            id = "https://i.ibb.co/grwdbFL/icon-profile-8.png"
            aa.dismiss()
        }
        imageView9.setOnClickListener {
            imageView.setImageDrawable(imageView9.drawable)
            id = "https://i.ibb.co/n0CQ6N4/icon-profile-9.png"
            aa.dismiss()
        }
        imageView10.setOnClickListener {
            imageView.setImageDrawable(imageView10.drawable)
            id = "https://i.ibb.co/54T09DR/icon-profile-10.png"

            aa.dismiss()
        }
        imageView11.setOnClickListener {
            imageView.setImageDrawable(imageView11.drawable)
            id = "https://i.ibb.co/NC7jXQz/icon-profile-11.png"
            aa.dismiss()
        }
        imageView12.setOnClickListener {
            imageView.setImageDrawable(imageView12.drawable)
            id = "https://i.ibb.co/Hh4hrTJ/icon-profile-12.png"
            aa.dismiss()
        }

        binding.submitPer.setOnClickListener {
            Prefs.Builder()
                    .setContext(this)
                    .setMode(ContextWrapper.MODE_PRIVATE)
                    .setPrefsName(packageName)
                    .setUseDefaultSharedPreference(true)
                    .build()
            if(binding.EditPerCon.text.isEmpty()||binding.EditNickName.text.isEmpty()||binding.EditMobileNumber.text.isEmpty()||id.equals("")){
                Toast.makeText(this, "The Field is Required", Toast.LENGTH_LONG).show()
            }
            else {




                if(binding.EditMobileNumber.text.toString().length <= 9 ){
                    Toast.makeText(this, "Number Must Be 10 Digit ", Toast.LENGTH_SHORT).show()
                }

          else {

              if (Prefs.getString("Contact_Name", "").equals("")) {
                        Prefs.putString("Contact_Name", binding.EditPerCon.text.toString())
                        Prefs.putString("Nick_Name", binding.EditNickName.text.toString())
                        Prefs.putString("Contact_Number", binding.EditMobileNumber.text.toString())
                        Prefs.putString("Contact_Profile", id)
                        startActivity(Intent(this, HomeActivty::class.java).putExtra("rcv", "per"))
                        finish()
                    } else {
                        Prefs.putString("Contact_Name", Prefs.getString("Contact_Name", "") + "," + binding.EditPerCon.text.toString())
                        Prefs.putString("Nick_Name", Prefs.getString("Nick_Name", "") + "," + binding.EditNickName.text.toString())
                        Prefs.putString("Contact_Number", Prefs.getString("Contact_Number", "") + "," + binding.EditMobileNumber.text.toString())
                        Prefs.putString("Contact_Profile", Prefs.getString("Contact_Profile", "") + "," + id)
                        startActivity(Intent(this, HomeActivty::class.java).putExtra("rcv", "per"))
                        finish()
                    }
                }

            }
        }









    }
}