package com.example.database_one

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.database_one.database.DatabaseHelper
import com.example.database_one.models.ContactModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var dbHelper = DatabaseHelper(this@MainActivity)

        dbHelper.addContact("Babu Bisleri","9564632584")
        dbHelper.addContact("Chamunda","8654267845")
        dbHelper.addContact("Khali","7965842325")
        dbHelper.addContact("Chota Don","8796534295")
        dbHelper.addContact("RDX Bhai","9676842685")
        dbHelper.addContact("Chulbul","7742566889")
        dbHelper.addContact("Sanjay","8961502010")
        dbHelper.addContact("Amrish Puri","8354867748")
        dbHelper.addContact("Samrat","8820566935")

//        var contactModel : ContactModel = ContactModel()
//        contactModel.id=1
//        contactModel.name="Bisleri"
//        contactModel.phoneNumber="1121332123"
//
//        dbHelper.updateContact(contactModel)


        var arrContact : ArrayList<ContactModel> = ArrayList()
        arrContact=dbHelper.fetchContact()
        for (i in arrContact.indices) {
            Log.d("Contact Info","Name : ${arrContact.get(i).name} Phone No : ${arrContact.get(i).phoneNumber}")
        }
    }
}