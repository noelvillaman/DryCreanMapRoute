package com.software.namalliv.drycreanmaproute.util

import android.view.View
import android.widget.Button

object ExtensionsFunctions {

    fun View.show(){
        this.visibility = View.VISIBLE
    }

    fun View.hide(){
        this.visibility = View.GONE
    }

    fun Button.enable(){
        this.isEnabled = true
    }

    fun Button.disable(){
        this.isEnabled = false
    }
}