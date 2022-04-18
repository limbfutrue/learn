package com.libaoming.kotlinproject

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.Method

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()

    /**
     * 扩展方法里的this代表是T
     */
    protected fun <T> T.showToast(): T {
        Toast.makeText(this@BaseActivity, this.toString(), Toast.LENGTH_SHORT).show()
        return this
    }

    protected fun <T> T.logI() = Log.i("limb", this.toString())
    protected fun <T> T.logW() = Log.w("limb", this.toString())
    protected fun <T> T.logD() = Log.d("limb", this.toString())
    protected fun <T> T.logE() = Log.e("limb", this.toString())


    /**
     * act跳转
     */
    protected inline fun <reified T : Activity> start() {
        startActivity(Intent(this, T::class.java))
    }

    /**
     * act跳转
     */
    protected inline fun <reified T : Activity> startWithBundle(bundle:Bundle) {
        val intent = Intent(this, T::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    /**
     * View扩展点击事件
     */
    protected fun <T:View> T.click(action: (T) -> Unit) = this.setOnClickListener { action(this) }

}