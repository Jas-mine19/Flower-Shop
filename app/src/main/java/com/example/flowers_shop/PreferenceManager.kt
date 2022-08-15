package com.example.flowers_shop

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    val private_mode = 0

    private val pref_name = "SharedPreference"
    private val is_login = "is_login"

    val pref: SharedPreferences = context.getSharedPreferences(pref_name,private_mode)

    val editor:SharedPreferences.Editor = pref.edit()

    fun setLogin(isLogin:Boolean){
        editor.putBoolean(is_login,isLogin)
        editor.commit()
    }

    fun setUsername(username: String){
        editor.putString("username",username)
        editor.commit()
    }

    fun isLogin() :Boolean{
        return  pref.getBoolean(is_login,false)
    }
    fun setUsername() : String? {
        return  pref.getString("username","")
    }

    fun removeData(){
        editor.clear()
        editor.commit()
    }


}