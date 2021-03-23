package com.example.andoid.weatherkotlin.db

import android.provider.BaseColumns

object MyDbNameClass {
    const val TABLE_NAME = "my_table"
    const val COLUMN_NAME_CITY = "city"
    const val COLUMN_NAME_TEMP = "temp"
    const val COLUMN_NAME_MIN = "temp_min"
    const val COLUMN_NAME_MAX = "temp_max"
    const val COLUMN_NAME_PRES = "pressure"
    const val COLUMN_NAME_HUM = "humidity"
    const val COLUMN_NAME_SUNR = "sunrise"
    const val COLUMN_NAME_SUNS = "sunset"
    const val COLUMN_NAME_SPEED = "speed"
    const val COLUMN_NAME_DESC = "description"
    const val COLUMN_NAME_COUNTRY = "country"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "WeatherCities.db"


    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_CITY TEXT," +
            "$COLUMN_NAME_TEMP TEXT,$COLUMN_NAME_MIN TEXT,$COLUMN_NAME_MAX TEXT" +
            ",$COLUMN_NAME_PRES TEXT,$COLUMN_NAME_HUM TEXT,$COLUMN_NAME_SUNR TEXT," +
            "$COLUMN_NAME_SUNS TEXT,$COLUMN_NAME_SPEED TEXT,$COLUMN_NAME_DESC TEXT,$COLUMN_NAME_COUNTRY TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

}