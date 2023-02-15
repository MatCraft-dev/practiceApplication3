package com.example.practiceapplication3

import android.app.Application
import com.google.android.material.color.DynamicColors

class MyPracticeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }


}



