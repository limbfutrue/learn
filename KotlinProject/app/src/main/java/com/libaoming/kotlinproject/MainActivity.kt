package com.libaoming.kotlinproject

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.libaoming.kotlinproject.adapter.RvAdapter
import com.libaoming.kotlinproject.bean.HomeData
import com.libaoming.kotlinproject.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var adapter: RvAdapter

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initData()
    }

    private fun initView() {
        val rvManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = rvManager
        adapter = RvAdapter(this)
        binding.rvList.adapter = adapter


    }

    private fun initData(){
        var listData:ArrayList<HomeData> = ArrayList()

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