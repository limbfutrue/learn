package com.libaoming.kotlinproject

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.libaoming.kotlinproject.adapter.RvAdapter
import com.libaoming.kotlinproject.bean.HomeData
import com.libaoming.kotlinproject.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var adapter: RvAdapter

    override val binding by bindLayout<ActivityMainBinding>()

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

        binding.btClick.click {
            "kotlin扩展函数学习".logD()
            "kotlin扩展函数学习".showToast()
            start<Main2Activity>()
        }


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