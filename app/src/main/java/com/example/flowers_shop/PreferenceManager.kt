package com.example.flowers_shop

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    private val prefName = "SharedPreference"
    private val isLogin2 = "is_login"

    private val pref: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    private val editor: SharedPreferences.Editor = pref.edit()

    fun setLogin(isLogin: Boolean) {
        editor.putBoolean(isLogin2, isLogin)
        editor.commit()
    }


    fun isLogin(): Boolean {
        return pref.getBoolean(isLogin2, false)
    }

    fun removeData() {
        editor.clear()
        editor.commit()
    }


}