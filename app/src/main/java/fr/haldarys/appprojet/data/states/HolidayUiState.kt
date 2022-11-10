package fr.haldarys.appprojet.data.states

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.*

data class HolidayUiState @RequiresApi(Build.VERSION_CODES.O) constructor(
    val name: String = "",
    val date: String = "",
    val location: String = ""
)