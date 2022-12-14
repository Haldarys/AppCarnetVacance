package fr.haldarys.appprojet.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import javax.annotation.Nonnull

/*
@Entity(tableName = "locations", foreignKeys = arrayOf(
    ForeignKey(entity = VisitModel::class,
    parentColumns = arrayOf("location_id"),
        childColumns = arrayOf("visit_id"),
        onDelete = ForeignKey.CASCADE)
))
*/
@Entity(tableName = "locations")
data class LocationModel (
    //@Transient
    @PrimaryKey
    val location_id : Int,

    @Nonnull @ColumnInfo(name = "houseNumber")
    var house_number: Int,

    @Nonnull @ColumnInfo(name = "city")
    var city: String = "",

    @ColumnInfo(name = "road")
    var road: String = "",

    @ColumnInfo(name = "state")
    var state: String = "",

    @ColumnInfo(name = "region")
    var region: String = "",

     @ColumnInfo(name = "postCode")
    var postCode: String = "",

    @ColumnInfo(name = "country")
    var country: String = ""
)