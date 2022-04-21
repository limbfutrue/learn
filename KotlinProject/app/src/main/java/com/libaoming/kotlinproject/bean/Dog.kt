package com.libaoming.kotlinproject.bean

import android.content.Context

/**
 * Kotlin单例模式
 */
class Dog private constructor(con: Context?) {

    companion object{
        // 方式1
//        val instance:Dog by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { Dog(null) }

        // 方式2
        @Volatile
        private var mInstance : Dog? = null
        fun getInstance(con:Context): Dog{
            if (mInstance == null){
                synchronized(Person::class.java){
                    if (mInstance == null){
                        mInstance = Dog(con)
                    }
                }
            }
            return mInstance!!
        }

        @JvmStatic
        fun main(args:Array<String>){
            println(Dog::class)
            println(Dog::class.java)
            println(Dog.javaClass)
            A.aa()
        }
    }

    object A{
        fun aa(){
            println("object A" + A.javaClass)
        }
    }
}