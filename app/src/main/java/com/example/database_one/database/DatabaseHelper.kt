package com.example.database_one.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.database_one.models.ContactModel

class DatabaseHelper(context: Context) : SQLiteOpenHelper (context, database_name,null,
    database_version) {

    companion object{

        private const val  database_name = "contacts.DB"
        private const val database_version = 1
        private const val tableContact = "contacts"
        private const val keyId = "id"
        private const val keyname = "name"
        private const val keyPhoneNo = "phoneNo"

    }

    override fun onCreate(p0: SQLiteDatabase?) {

        p0?.execSQL("CREATE TABLE $tableContact ($keyId INTEGER PRIMARY KEY AUTOINCREMENT, $keyname TEXT, $keyPhoneNo TEXT)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $tableContact")
        onCreate(p0)
    }

    public fun addContact (name : String, number : String){
        var db : SQLiteDatabase = writableDatabase
        var values = ContentValues()
        values.put(keyname,name)
        values.put(keyPhoneNo,number)
       var id = db.insert(tableContact,null,values)
    }

    public fun fetchContact () : ArrayList<ContactModel>
    {
        var db : SQLiteDatabase
        db = this.readableDatabase
        var cursor : Cursor
        cursor = db?.rawQuery("SELECT *FROM $tableContact",null)!!
        var arrContacts : ArrayList<ContactModel> = ArrayList()
        while (cursor.moveToNext())
        {
            var model = ContactModel()
            model.id=cursor.getInt(0)
            model.name=cursor.getString(1)
            model.phoneNumber=cursor.getString(2)
            arrContacts.add(model)
        }
        return arrContacts
    }

    public fun updateContact(contactModel: ContactModel){
        var db : SQLiteDatabase
        db = this.writableDatabase
        var values = ContentValues()
        values.put(keyPhoneNo,contactModel.phoneNumber)
        values.put(keyname,contactModel.name)
        db.update(tableContact,values,"$keyId = ${contactModel.id}",null)
    }

    public fun deleteContact(Id : Int){
        var db : SQLiteDatabase
        db=this.writableDatabase
        db.delete(tableContact,"$keyId =? ", arrayOf(Id.toString()))
    }
}