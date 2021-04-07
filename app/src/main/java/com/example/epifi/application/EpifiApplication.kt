package com.example.epifi.application

import android.app.Application
import android.content.Context


class EpifiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        context=applicationContext

    }
    companion object{

      lateinit var context:Context

    }




}