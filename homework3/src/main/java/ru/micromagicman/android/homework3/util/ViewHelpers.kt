package ru.micromagicman.android.homework3.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import java.math.BigDecimal

fun EditText.onTextChanged(textChangeListener: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // do nothing
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // do nothing
        }

        override fun afterTextChanged(editable: Editable?) {
            textChangeListener.invoke(editable.toString())
        }
    })
}

fun EditText.intValue(): Int = text.toString().run {
    try {
        if (isBlank()) 0 else toInt()
    } catch (numberFormatException: NumberFormatException) {
        return Int.MAX_VALUE
    }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}