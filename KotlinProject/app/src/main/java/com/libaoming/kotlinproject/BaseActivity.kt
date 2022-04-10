package com.libaoming.kotlinproject

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    fun <T> T.showToast(): T {
        Toast.makeText(this@BaseActivity, this.toString(), Toast.LENGTH_SHORT).show()
        return this
    }

    fun <T>T.logE():T{
        Log.e("limb",this.toString())
        return this
    }

    fun <T>T.logD():T{
        Log.d("limb",this.toString())
        return this
    }
}