package com.example.andoid.weatherkotlin

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.andoid.weatherkotlin.db.MyAdapter
import com.example.andoid.weatherkotlin.db.MyDbManager
import kotlinx.android.synthetic.main.cities.*
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class Cities : AppCompatActivity () {

    val myDbManager = MyDbManager(this)
    val myAdapter = MyAdapter(ArrayList(), this)


   /*val CITY1: String = "London"
    val CITY2: String = "Obukhiv"
    val CITY3: String = "Amsterdam"*/
    val API: String = "82b8e43fce7be48dea0844e12e5d494c"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cities)
        init()

     /*   weatherTask1().execute()
        weatherTask2().execute()
        weatherTask3().execute()*/
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        fillAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }

    fun init (){

        rcView.layoutManager = LinearLayoutManager(this)
        val swapHelper = getSwapMg()
        swapHelper?.attachToRecyclerView(rcView)
        rcView.adapter = myAdapter
    }

    fun fillAdapter (){

        val list = myDbManager.readDbData("")
        myAdapter.updateAdapter(list)


    }

    private fun getSwapMg(): ItemTouchHelper? {
        return ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myAdapter.removeItem(viewHolder.adapterPosition, myDbManager)
            }
        })
    }



    fun onClickSave(view: View) {
        val newCity = addCity.text.toString()

         class weatherTask1 : AsyncTask<String, Void, String>(){

            override fun onPreExecute() {
                super.onPreExecute()

            }

            override fun doInBackground(vararg params: String?): String {
                var response:String?
                try {
                    response =
                        URL("https://api.openweathermap.org/data/2.5/weather?q=$newCity&units=metric&appid=$API")
                            .readText(Charsets.UTF_8)

                }catch (e: Exception)
                {
                    response = null
                }
                return response.toString()
            }

            override fun onPostExecute(result: String?) {
                   try {
                    val jsonObj = JSONObject(result)
                    val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                    val main = jsonObj.getJSONObject("main")
                    val sys = jsonObj.getJSONObject("sys")
                    val wind = jsonObj.getJSONObject("wind")
                    val temp = main.getString("temp").substringBefore(".") + "°C"
                    val tempMin =  main.getString("temp_min").substringBefore(".") + "°C"
                    val tempMax =  main.getString("temp_max").substringBefore(".") + "°C"
                    val windSpeed = wind.getString("speed")
                    val sunrise: Long = sys.getLong("sunrise")
                    val sunset: Long = sys.getLong("sunset")
                    val weatherDescription = weather.getString("description")
                    val address = jsonObj.getString("name") + ", " + sys.getString("country")

                    if (newCity != "") {

                        myDbManager.insertToDb(newCity, temp, tempMin, tempMax, main.toString(), main.toString(),
                            sunrise.toString(), sunset.toString(), windSpeed, weatherDescription, address )

                    }
                } catch (e: Exception)
                {

                }
            }
        }

        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show()


        myDbManager.closeDb()
        finish()

    }







}
