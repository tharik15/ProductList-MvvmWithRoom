package com.example.shopping

import android.app.Application
import com.facebook.stetho.Stetho

class ApplicationClass :Application(){


    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

}