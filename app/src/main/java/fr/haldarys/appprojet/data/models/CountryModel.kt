package fr.haldarys.appprojet.data.models

import androidx.room.*
import javax.annotation.Nonnull

@Entity(tableName = "Countries")
data class CountryModel (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "countryId")
    var country_id : Int = 0,

    @Nonnull @ColumnInfo(name = "name")
    var name : String,

    @Nonnull @ColumnInfo(name = "iso2")
    var iso2 : String,

    @Nonnull @ColumnInfo(name = "long")
    var long : String,

    @Nonnull @ColumnInfo(name = "lat")
    var lat : String
){
    constructor()
            : this(0,"","","","")
}
