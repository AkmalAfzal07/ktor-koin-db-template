package com.example.koinstructure.data.preference

import android.content.Context
import android.content.SharedPreferences
import com.example.koinstructure.utils.AppConstant

class AppPreferences (context: Context){



    private val pref:SharedPreferences by lazy {
        context.getSharedPreferences(AppConstant.PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private val editor:SharedPreferences.Editor by lazy {
        pref.edit()
    }

    fun setString(key: String, value:String) {
        editor.putString(key, value).apply()
    }

    fun getString(key: String): String {
        return pref.getString(key, null).toString()
    }
}