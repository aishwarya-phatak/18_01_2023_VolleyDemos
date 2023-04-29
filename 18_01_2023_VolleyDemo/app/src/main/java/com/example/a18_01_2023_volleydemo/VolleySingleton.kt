package com.example.a18_01_2023_volleydemo

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

object VolleySingleton {
    var volleyRequestQueue : RequestQueue? = null
    fun initializeRequestQueue(context : Context){
        volleyRequestQueue = Volley.newRequestQueue(context)
    }
}