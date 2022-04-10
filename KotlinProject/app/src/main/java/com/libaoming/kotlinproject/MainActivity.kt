package com.libaoming.kotlinproject

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import com.libaoming.kotlinproject.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val list:ArrayList<String> = ArrayList()
        list.apply {
            add("123")
            add("123")
            add("123")
            add("123")
            add("123")
        }

        val adp = Adapter(this,list)
        binding.lv.adapter = adp

        binding.lv.onItemClickListener = AdapterView.OnItemClickListener {
                _, _, position, _ ->
            list[position].showToast()
            (list[position].logE()+"123").logD()
            startActivity(Intent(this,Main2Activity::class.java))
        }
    }

}