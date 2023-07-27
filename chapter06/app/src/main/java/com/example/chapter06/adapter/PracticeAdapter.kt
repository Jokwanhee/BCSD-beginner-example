package com.example.chapter06.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter06.R

class PracticeAdapter(
    val itemList: MutableList<String>,
    val listener: Context
) : RecyclerView.Adapter<PracticeAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.text_view_item_name)
    }

    interface OnClickListener {
        fun onClick(position: Int)
        fun onLongClick(position: Int)
    }

    private lateinit var setOnClickListener: OnClickListener
    private lateinit var setOnLongClickListener: OnClickListener

    private fun setOnClick(listener: OnClickListener) {
        setOnClickListener = listener
    }

    private fun setOnLongClick(listener: OnClickListener) {
        setOnLongClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("로그", "onCreateViewHolder()")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_name_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("로그", "onBindViewHolder()")
        holder.name.text = itemList[position]
        holder.itemView.setOnClickListener {
            setOnClick(listener as OnClickListener)
            setOnClickListener.onClick(position)
        }
        holder.itemView.setOnLongClickListener {
            setOnLongClick(listener as OnClickListener)
            setOnLongClickListener.onLongClick(position)
            false
        }
    }

    override fun getItemCount(): Int = itemList.size
//        Log.d("로그", "getItemCount()")
//        return itemList.size
//    }

}