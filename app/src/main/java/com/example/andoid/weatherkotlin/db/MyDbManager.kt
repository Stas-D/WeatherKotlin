package com.example.andoid.weatherkotlin.db

import android.app.LauncherActivity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.View
import android.widget.Toast
import com.example.andoid.weatherkotlin.ListItem
import com.example.andoid.weatherkotlin.R

class MyDbManager (context: Context){
    val myDbHelper = MyDbHelper (context)
    var db: SQLiteDatabase? = null

    fun  openDb (){
        db = myDbHelper.writableDatabase
    }

    fun insertToDb(
        city: String, temp: String, temp_min: String, temp_max: String,
        pressure: String, humidity: String, sunrise: String, sunset: String,
        speed: String, description: String, country: String)
    {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_CITY, city)
            put(MyDbNameClass.COLUMN_NAME_TEMP, temp)
            put(MyDbNameClass.COLUMN_NAME_MIN, temp_min)
            put(MyDbNameClass.COLUMN_NAME_MAX, temp_max)
            put(MyDbNameClass.COLUMN_NAME_PRES, pressure)
            put(MyDbNameClass.COLUMN_NAME_HUM, humidity)
            put(MyDbNameClass.COLUMN_NAME_SUNR, sunrise)
            put(MyDbNameClass.COLUMN_NAME_SUNS, sunset)
            put(MyDbNameClass.COLUMN_NAME_SPEED, speed)
            put(MyDbNameClass.COLUMN_NAME_DESC, description)
            put(MyDbNameClass.COLUMN_NAME_COUNTRY, country)

        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }




    fun removeItemFromDb ( id: String){
        val selection = BaseColumns._ID + "=$id"
        db?.delete(MyDbNameClass.TABLE_NAME, selection, null)

    }



    fun readDbData(searchText:String) : ArrayList<ListItem> {
        val dataList = ArrayList<ListItem>()
        val selection = "${MyDbNameClass.COLUMN_NAME_CITY} like ?"
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null, null,
            null, null, null)


        while (cursor?.moveToNext()!!) {
            val dataCity = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_CITY))
            val dataTemp = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TEMP))
            val dataMin = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_MIN))
            val dataId = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val dataMax = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_MAX))
            val dataPres = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_PRES))
            val dataHum = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_HUM))
            val dataSunr = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_SUNR))
            val dataSuns = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_SUNS))
            val dataSpeed = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_SPEED))
            val dataDesc = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_DESC))
            val dataCountry = cursor.getString(cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_COUNTRY))
            val item = ListItem()
            item.city = dataCity
            item.temp = dataTemp
            item.temp_min = dataMin
            item.id = dataId
            item.temp_max = dataMax
            item.pressure = dataPres
            item.humidity = dataHum
            item.sunrise = dataSunr
            item.sunset = dataSuns
            item.speed = dataSpeed
            item.description = dataDesc
            item.country = dataCountry
            dataList.add(item)
        }
        cursor.close()
        return dataList
    }


    fun closeDb(){
        myDbHelper.close()

    }





    }