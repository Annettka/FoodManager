package by.it.academy.foodmanager.app.domain.converters

import java.text.SimpleDateFormat
import java.util.*

class DateStringConverter {

    fun dateToString(value: Long): String =
        SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(value)

    fun stringToDate(value: String): Long {
        val date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(value)
        return date.time
    }
}