package com.example.flowers_shop.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flowers_shop.models.Api
import com.example.flowers_shop.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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