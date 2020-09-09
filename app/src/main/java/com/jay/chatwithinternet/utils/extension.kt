package com.jay.chatwithinternet.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment

public fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

public fun Fragment.toast(msg: String) {
    Toast.makeText(activity,msg,Toast.LENGTH_LONG).show()
}