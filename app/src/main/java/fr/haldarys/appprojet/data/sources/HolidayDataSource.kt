package fr.haldarys.appprojet.data.sources

import fr.haldarys.appprojet.data.models.HolidayModel

class HolidayDataSource {
    private var _holiday: HolidayModel = HolidayModel("my_db_id", "My Awesome Holiday", "Someday", "Somewhere")
    var latestHoliday : HolidayModel
        get() = _holiday
        set(value) {_holiday = value}
}