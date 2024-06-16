package com.example.todolist.util

import androidx.navigation.NavController

interface BackPressedListener {
    fun setHistoryNavController(navController: NavController)
    fun setHomeNavController(navController: NavController)
}