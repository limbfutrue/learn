package com.libaoming.kotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("limb","测试git123")
        Log.e("limb","测试git456")
        Log.e("limb","测试git789")
        Log.e("limb","测试git000")
    }
}