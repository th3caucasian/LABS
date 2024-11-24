package com.example.labs

import android.annotation.SuppressLint
import android.content.Context

// ВЫЗОВ ФУНКЦИИ clearContext() обязателен после его создания!!
@SuppressLint("StaticFieldLeak")
object ContextProvider {

    private var activityContext: Context? = null

    fun initialize(context: Context) {
        activityContext = context
    }

    fun getContext(): Context {
        return activityContext ?: throw IllegalStateException("ContextProvider is not initialized")
    }

    fun clearContext() {
        activityContext = null
    }

}