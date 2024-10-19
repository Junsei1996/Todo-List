package com.example.todolist.util

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import com.example.todolist.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object UtilityFunctions {

    public fun test(){
        Log.d("TEST","TEST SUCCESSFUL")
    }

    fun showAlertDialog(context: Context, title:String, msg:String, listener:AlertDialogListener){

        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(msg) // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(
                R.string.delete
            ) { dialog, which ->
                listener.onAccept()
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(R.string.cancel, null)
            .show()

    }

    fun isTimePassed(inputTime: String?): Boolean {
        // Define the date format
        val dateFormat = SimpleDateFormat("dd-MMM-yyyy HH:mm", Locale.getDefault())

        return try {
            // Parse the input time string to a Date object
            val parsedDate: Date = inputTime?.let { dateFormat.parse(it) } ?: return false

            // Get the current time
            val currentTime: Date = Date()

            // Compare the input time with the current time
            parsedDate.before(currentTime)
        } catch (e: Exception) {
            // Handle the exception (e.g., parsing error)
            e.printStackTrace()
            false // Return false if there's an error
        }
    }

}