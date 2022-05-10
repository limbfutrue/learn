package com.libaoming.kotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.libaoming.kotlinproject.databinding.ActivityMain2Binding

class Main2Activity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}