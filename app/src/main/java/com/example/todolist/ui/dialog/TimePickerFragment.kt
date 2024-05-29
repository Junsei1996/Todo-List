package com.example.todolist.ui.dialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.example.todolist.util.DateAndTimeListener
import java.util.Calendar

class TimePickerFragment() : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    lateinit var timeListener: DateAndTimeListener

    constructor(listener:DateAndTimeListener):this(){
        this.timeListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker.
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it.
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        timeListener.onTimeSelected(hourOfDay.toString()+" "+minute.toString())
    }
}