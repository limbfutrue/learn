package com.libaoming.kotlinproject

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding


/**
 * Activity ViewBinding通用封装
 * 内联函数，将代码直接代入调用的地方,内联函数可以避免入栈出栈的消耗，但是注意内联函数不要过大
 * @param isRoot 是否将viewbinding作为activity的根布局
 */
inline fun <reified VB : ViewBinding> Activity.inflate(isRoot: Boolean) = lazy {
    if (isRoot)
        inflateBinding<VB>(layoutInflater).apply { setContentView(root) }
    else
        inflateBinding(layoutInflater)
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VB : ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB
