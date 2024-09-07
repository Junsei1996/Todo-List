package com.example.todolist.ui.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.todolist.util.DateAndTimeListener
import java.text.SimpleDateFormat
import java.util.Calendar

class DatePickerFragment() : DialogFragment(), DatePickerDialog.OnDateSetListener {

    lateinit var dateListener: DateAndTimeListener

    constructor(listener: DateAndTimeListener):this(){
        this.dateListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker.
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it.
        return DatePickerDialog(requireContext(), this, year, month, day)

    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date the user picks.
        val sdf = SimpleDateFormat("dd-MMM-yyyy")
        var calendar : Calendar = Calendar.getInstance();
        calendar.set(year, month, day)
        val dateString: String = sdf.format(calendar.getTime())
        dateListener.onDateSelected(dateString)
    }
}