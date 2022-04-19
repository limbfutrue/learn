package com.libaoming.kotlinproject.viewmodule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModule : ViewModel() {
    fun requestData(list: MutableLiveData<String>) {
        list.value = "HomeViewModule"
    }

}