package fr.haldarys.appprojet.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import fr.haldarys.appprojet.data.sources.CountryCacheSource

class RefreshCountriesWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext,params) {
        override suspend fun doWork():Result{
            try{
                CountryCacheSource.refreshCountries()
            }
            catch (e: Exception){
                return Result.retry()
            }
            return Result.success()
        }
}