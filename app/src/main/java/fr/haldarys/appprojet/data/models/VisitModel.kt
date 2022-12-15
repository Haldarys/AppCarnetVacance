package fr.haldarys.appprojet.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import javax.annotation.Nonnull

@Entity(tableName = "visits")
data class VisitModel(
    @PrimaryKey
    val visit_id : Int,
    @Nonnull @ColumnInfo(name = "location")
    val location : LocationModel,
    @Nonnull @ColumnInfo(name = "date")
    val date : Date
)
