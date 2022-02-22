package ru.gb.course1.di1.ui

import android.app.Activity
import android.content.Intent
import org.koin.core.annotation.Single

@Single
class Router {
    fun openScreen(activity: Activity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }
}