package com.andrewtsaturov.groupchat.presentation.common

import android.text.Editable
import android.text.TextWatcher

abstract class MvpTextWatcher: TextWatcher {

    abstract fun updateValue(value: CharSequence)

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = updateValue(s!!)

}