package com.example.hiltsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

//    private val _text = MutableLiveData<String>()
//    val text: MutableLiveData<String> = _text
//
//    fun setText(text: String) = _text.postValue(text)

    private val _res = MutableLiveData<Resource<AppVersionResponse>>()
    val res: LiveData<Resource<AppVersionResponse>> get() = _res

    fun getBaseUrl() {
        repository.getBaeUrl().let {
            Timber.d("baseUrl => $it")
        }
    }

    suspend fun getAppVersion() {

        repository.callAppVersion().let {

            coroutineScope {
                _res.postValue(Resource.loading(null))
                try {
                    val response = it
                    if (response.result == ResponseType.SUCCESS) {
                        _res.postValue(Resource.success(response.body))
                    } else {
                        _res.postValue(Resource.error(response.message.toString(), null))
                    }
                } catch (e: Exception) {
                    _res.postValue(Resource.error(e.message.toString(), null))
                }
            }

        }

    }

}