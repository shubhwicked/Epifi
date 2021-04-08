package com.example.epifi.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

/**
 * Abstract class to validate edittext text, it will simply implement this class and return the result with text and textview.
 */
abstract class TextValidator(private val textView: EditText?) : TextWatcher {

    abstract fun validate(textView: EditText?, text: String?)
    override fun afterTextChanged(s: Editable?) {
        val text: String = textView!!.text.toString()
        validate(textView, text)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { /* Don't care */
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { /* Don't care */
    }

}