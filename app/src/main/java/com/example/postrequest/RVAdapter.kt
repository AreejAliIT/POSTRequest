package com.example.postrequest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val data: ArrayList<String>) :
        RecyclerView.Adapter<RVAdapter.ViewHolder>(){

        class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)
        // for binding
      lateinit var tv : TextView
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_row,
                    parent,
                    false
                )
            )
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg = data[position]
        holder.itemView.apply {
            tv = findViewById(R.id.textView)
            tv.text = msg
        }
     }

       override fun getItemCount() = data.size

}

