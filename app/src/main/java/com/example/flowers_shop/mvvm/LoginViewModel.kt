package com.example.flowers_shop.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowers_shop.data.Api
import com.example.flowers_shop.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginViewModel : ViewModel() {

    private val apiInterface = Api().create()

    private val _loginLiveData = MutableLiveData<Boolean>()
    val loginLiveData = _loginLiveData

    private val _errorLoginLiveData = MutableLiveData<String>()
    val errorLoginLiveData = _errorLoginLiveData


    private suspend fun login(user: User) = withContext(Dispatchers.IO) {
        apiInterface.login(user).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    _loginLiveData.postValue(true)
                } else {
                    _errorLoginLiveData.postValue(response.message().toString())
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                _errorLoginLiveData.postValue(t.message.toString())
            }

        })
    }



}