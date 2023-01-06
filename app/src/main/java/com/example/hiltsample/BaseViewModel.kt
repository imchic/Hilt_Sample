package com.example.hiltsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    private val _snackBar = MutableLiveData<String>()
    val snackBar: MutableLiveData<String> get() = _snackBar

    fun setMessage(string: String) {
        _snackBar.postValue(string)
    }

}
