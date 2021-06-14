package by.it.academy.foodmanager.presentation.ui.pickers

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker

import androidx.fragment.app.DialogFragment
import by.it.academy.foodmanager.presentation.ui.viewmodels.AddEditProductViewModel
import java.util.*

class TimePickerFragment(private val viewModelEdit: AddEditProductViewModel) : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val startTime: Long = hourOfDay.toLong() * 3600000 + minute.toLong() * 60000
        viewModelEdit.setNotificationTime(startTime)
    }

}