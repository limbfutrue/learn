package com.libaoming.kotlinproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(content:Context,list:ArrayList<String>) : BaseAdapter() {

    private var list1:ArrayList<String> = list
    private var con : Context = content

    override fun getCount(): Int {
        return list1.size
    }

    override fun getItem(position: Int): Any {
        return list1[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view : View?
        val holder : MyViewHolder?
        if (convertView == null){
            view = LayoutInflater.from(con).inflate(R.layout.item_layout,parent,false)
            holder = MyViewHolder(view)
            view?.tag = holder
        } else {
            view = convertView
            holder = view.tag as MyViewHolder
        }
        holder.title?.text = list1[position]
        return view!!
    }

    class MyViewHolder(view:View): RecyclerView.ViewHolder(view) {
        var title:TextView? = view.findViewById(R.id.title)
    }
}