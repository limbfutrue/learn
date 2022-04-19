package com.libaoming.kotlinproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.libaoming.kotlinproject.adapter.RvAdapter
import com.libaoming.kotlinproject.bean.HomeData
import com.libaoming.kotlinproject.databinding.ActivityMainBinding
import com.libaoming.kotlinproject.sample.room.RoomActivity
import com.libaoming.kotlinproject.viewmodule.HomeViewModule

class MainActivity : BaseActivity() {

    private lateinit var adapter: RvAdapter
    private var list = MutableLiveData<String>()
    private val binding by inflate<ActivityMainBinding>(true)
    private var homeModule:HomeViewModule?=null

    override fun initView() {
        val rvManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = rvManager
        adapter = RvAdapter(this)
        binding.rvList.adapter = adapter

        binding.btClick.click {
            "kotlin扩展函数学习".logD()
            "kotlin扩展函数学习".showToast()
            start<RoomActivity>()
        }

        list.observe(this){
            Log.e("limb","改变了$it")
            list.value.logE()
        }

        homeModule = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(HomeViewModule::class.java)
        homeModule!!.requestData(list)
        method(list.value ?: "ll"){it,i ->
            (it+i).logE()
        }
    }


    private fun method(str:String,add:(str:String,i:Int)->Unit){
        add(str,0)
    }

    override fun initData(){
        val listData:ArrayList<HomeData> = ArrayList()
        listData.add(HomeData(1,"type1","style1"))
        listData.add(HomeData(2,"type2","style2"))
        listData.add(HomeData(1,"type1","style1"))
        listData.add(HomeData(1,"type1","style1"))
        listData.add(HomeData(1,"type1","style1"))
        listData.add(HomeData(2,"type2","style2"))
        listData.add(HomeData(2,"type2","style2"))
        adapter.setData(listData)
    }

}