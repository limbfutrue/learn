package com.libaoming.kotlinproject.bean

import android.util.Log

class Person(name: String) {
    var name: String? = name
    var age: Int = 18
    var sex: String? = "男"
    var type: Int = 0
    var height: Int = 175

    constructor(name: String, age: Int) : this(name) {
        Log.e("limb", "constructor(name: String,age:Int)")
    }

    init {
        Log.e("limb", "init")
    }
}