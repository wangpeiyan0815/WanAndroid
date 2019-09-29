package com.wpy.wanandroid.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shehuan.wanandroid.utils.sp.SharedPreferencesHelper

object SearchHistoryUtils {

    private val KEY_HISTORY = "KEY_HISTORY"

    private val mGosn: Gson by lazy {
        Gson()
    }

    fun save(historys: List<String>) {
        if (historys.isEmpty()) {
            SharedPreferencesHelper.remove(KEY_HISTORY)
            return
        }
        val json = mGosn.toJson(historys)
        SharedPreferencesHelper.put(KEY_HISTORY, json)
    }

    fun get(): List<String>? {
        val json = SharedPreferencesHelper.get(KEY_HISTORY, "")
        if (json.isNullOrEmpty()) {
            return null
        }
        return try {
            mGosn.fromJson(json, object : TypeToken<List<String>>() {}.type)
        } catch (e: Exception) {
            SharedPreferencesHelper.remove(KEY_HISTORY)
            null
        }
    }
}