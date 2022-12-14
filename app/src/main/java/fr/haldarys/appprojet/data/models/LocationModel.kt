package fr.haldarys.appprojet.data.models

import androidx.room.*
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
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "locationId")
    var location_id : Int = 0,

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
) {
    constructor(house_number: Int, city: String, road: String, state: String, region: String, postCode: String, country: String)
        : this(1, house_number, city, road, state, region, postCode, country)
}