package fr.haldarys.appprojet

import android.app.Application
import fr.haldarys.appprojet.data.AppContainer
import fr.haldarys.appprojet.data.DefaultAppContainer

class PlanYourHolidaysApplication : Application() {
    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}