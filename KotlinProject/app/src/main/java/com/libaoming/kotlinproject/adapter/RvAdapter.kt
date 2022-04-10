package com.libaoming.kotlinproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.libaoming.kotlinproject.R
import com.libaoming.kotlinproject.bean.Constans
import com.libaoming.kotlinproject.bean.HomeData
import com.libaoming.kotlinproject.holder.BaseViewHolder

class RvAdapter(private var context:Context):RecyclerView.Adapter<BaseViewHolder>() {
    private var dataList = ArrayList<HomeData>()

    /**
     * 更新数据
     */
    fun setData(list:ArrayList<HomeData>){
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    /**
     *  创建ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when(viewType){
            Constans.type1->{
                val inflateView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
                return Type1ViewHolder(inflateView)
            }

            Constans.type2->{
                val inflateView = LayoutInflater.from(context).inflate(R.layout.item2_layout, parent, false)
                return Type2ViewHolder(inflateView)
            }
            else ->{
                val inflateView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
                return Type1ViewHolder(inflateView)
            }

        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(dataList[position].type){
            Constans.type1->{
                (holder as Type1ViewHolder).title.text = dataList[position].title
            }
            Constans.type2->{
                (holder as Type2ViewHolder).title.text = dataList[position].title
            }
            else->{
                (holder as Type1ViewHolder).title.text = dataList[position].title
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }

    class Type1ViewHolder(view:View): BaseViewHolder(view){
        var title: TextView = view.findViewById(R.id.title)
    }

    class Type2ViewHolder(view:View):BaseViewHolder(view){
        var title: TextView = view.findViewById(R.id.title)
    }
}