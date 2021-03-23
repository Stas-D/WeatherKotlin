package com.example.andoid.weatherkotlin.db

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.andoid.weatherkotlin.ListItem
import com.example.andoid.weatherkotlin.R

class MyAdapter(listMain: ArrayList<ListItem>, contextM: Context) : RecyclerView.Adapter<MyAdapter.MyHolder>(){
    var listArray = listMain
    var context = contextM

    class MyHolder(itemView: View, contextV: Context) : RecyclerView.ViewHolder(itemView) {
        val tvCity = itemView.findViewById<TextView>(R.id.tv_City)
        val tvTemp = itemView.findViewById<TextView>(R.id.tv_Temp)
        val tvHum = itemView.findViewById<TextView>(R.id.tv_Hum)
        val tvSpeed = itemView.findViewById<TextView>(R.id.tv_Speed)
        val context = contextV
        fun setData(item: ListItem){
            tvCity.text = item.city
            tvTemp.text = item.temp
            tvHum.text = item.humidity
           /* itemView.setOnClickListener{
                val intent = Intent(context, EditActivity::class.java).apply {

                    putExtra(MyIntentConstants.I_TITLE_KEY, item.title)
                    putExtra(MyIntentConstants.I_DISC_KEY, item.disc)
                    putExtra(MyIntentConstants.I_URI_KEY, item.uri)
                    putExtra(MyIntentConstants.I_ID_KEY, item.id)

                }
                context.startActivity(intent)
            }*/
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyAdapter.MyHolder(inflater.inflate(R.layout.rc_item, parent, false), context)



    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listArray.get(position))
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    fun updateAdapter(listItems: List<ListItem>) {
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()

    }


    fun removeItem(pos:Int, dbManager: MyDbManager) {
        dbManager.removeItemFromDb(listArray[pos].id.toString())
        listArray.removeAt(pos)
        notifyItemRangeChanged(0, listArray.size)
        notifyItemRemoved(pos)

    }

}