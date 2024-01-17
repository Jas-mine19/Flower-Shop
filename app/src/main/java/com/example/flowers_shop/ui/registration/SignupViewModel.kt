package com.example.flowers_shop.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowers_shop.models.Api
import com.example.flowers_shop.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupViewModel : ViewModel() {

    private val apiInterface = Api().create()

    private val _signupLiveData = MutableLiveData<Boolean>()
    val signupLiveData = _signupLiveData

    private val _errorSignupLiveData = MutableLiveData<String>()
    val errorSignupLiveData = _errorSignupLiveData

    // progressBar


    fun createUser(user: User) {
        viewModelScope.launch {
            getResponse(user)
        }
    }

    private suspend fun getResponse(user: User) = withContext(Dispatchers.IO) {
        apiInterface.registerUser(user).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    _signupLiveData.postValue(true)
                } else {
                    _errorSignupLiveData.postValue(response.message().toString())
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _errorSignupLiveData.postValue(t.message.toString())
            }

        })
    }

}