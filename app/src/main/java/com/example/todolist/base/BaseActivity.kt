package com.example.todolist.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {

    protected lateinit var binding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {

        binding = DataBindingUtil.setContentView(this, getActivityLayout())
        getViewBinding()
        setClickListeners()

    }

    abstract fun getActivityLayout(): Int

    abstract fun getViewBinding()

    abstract fun setClickListeners()

}