package com.libaoming.kotlinproject

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


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
/**
 * Fragment ViewBinding通用封装
 */
inline fun <reified VB : ViewBinding> Fragment.bindView() =
    FragmentBindingDelegate(VB::class.java)

inline fun <reified VB : ViewBinding> Fragment.inflateByBind() =
    FragmentBindingDelegate(VB::class.java)

inline fun <reified VB : ViewBinding> Fragment.inflateByInflate() =
    FragmentBindingDelegateInflate(VB::class.java)

class FragmentBindingDelegateInflate<VB : ViewBinding>(
    private val clazz: Class<VB>
) {

    private var isInitialized = false
    private var _binding: VB? = null
    private val binding: VB get() = _binding!!

    fun getViewBinding(thisRef: Fragment): VB {
        if (!isInitialized) {
            thisRef.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroyView() {
                    _binding = null
                }
            })
            _binding = clazz.getMethod("inflate", LayoutInflater::class.java)
                .invoke(null, LayoutInflater.from(thisRef.context)) as VB
            isInitialized = true
        }
        return binding
    }
}

class FragmentBindingDelegate<VB : ViewBinding>(
    private val clazz: Class<VB>
) : ReadOnlyProperty<Fragment, VB> {

    private var isInitialized = false
    private var _binding: VB? = null
    private val binding: VB get() = _binding!!

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        if (!isInitialized) {
            thisRef.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroyView() {
                    _binding = null
                }
            })
            _binding = clazz.getMethod("bind", View::class.java)
                .invoke(null, thisRef.view) as VB
            isInitialized = true
        }
        return binding
    }

    operator fun setValue(fragment: Fragment, property: KProperty<*>, vb: VB) {

    }
}