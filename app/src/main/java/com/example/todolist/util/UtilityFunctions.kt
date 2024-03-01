package com.example.todolist.util

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import com.example.todolist.R


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

}