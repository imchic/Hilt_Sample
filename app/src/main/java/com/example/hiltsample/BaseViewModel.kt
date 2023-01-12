package com.example.hiltsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

//    private val _snackBar = MutableLiveData<String>()
//    val snackBar: MutableLiveData<String> get() = _snackBar

    private val _snackBar = MutableSharedFlow<String>()
    val snackBar = _snackBar

    fun setMessage(string: String) {
        viewModelScope.launch {
            _snackBar.emit(string)
        }
    }


    // RxJava 관련
    private val compositeDisposable = CompositeDisposable()
    fun addDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}
