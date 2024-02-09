package com.example.todolist.util

import android.app.AlertDialog
import android.content.Context
import android.util.Log

object UtilityFunctions {

    public fun test(){
        Log.d("TEST","TEST SUCCESSFUL")
    }

    fun showAlertDialog(context: Context){

        var alertDialog = AlertDialog.Builder(context).create()

    }

}