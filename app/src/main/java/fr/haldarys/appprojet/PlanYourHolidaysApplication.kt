package fr.haldarys.appprojet

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import fr.haldarys.appprojet.data.AppContainer
import fr.haldarys.appprojet.data.DefaultAppContainer

@HiltAndroidApp
class PlanYourHolidaysApplication : Application() {
    companion object {
        private var sApplication: Application? = null

        fun getApplication(): Application? {
            return sApplication
        }

        fun getContext(): Context? {
            return getApplication()!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        sApplication = this
    }
}