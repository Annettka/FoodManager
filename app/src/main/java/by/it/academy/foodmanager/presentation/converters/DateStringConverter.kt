package by.it.academy.foodmanager.presentation.converters

import android.widget.TextView
import androidx.databinding.BindingConversion
import androidx.databinding.InverseMethod
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