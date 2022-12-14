package fr.haldarys.appprojet

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import fr.haldarys.appprojet.data.AppContainer
import fr.haldarys.appprojet.data.AppDatabase
import fr.haldarys.appprojet.data.DefaultAppContainer

@HiltAndroidApp
class PlanYourHolidaysApplication : Application() {
    val database : AppDatabase by lazy { AppDatabase.getDatabase(this)}
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