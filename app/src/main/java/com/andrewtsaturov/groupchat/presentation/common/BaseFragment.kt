package com.andrewtsaturov.groupchat.presentation.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment: MvpAppCompatFragment() {

    companion object {
        private const val PROGRESS_DIALOG_TAG = "progress"
    }

    abstract val layoutResource: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(layoutResource, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    open fun initView() = Unit

    override fun onDestroy() {
        super.onDestroy()
        hideSoftwareKeyboard()
    }

    protected fun hideSoftwareKeyboard() {
        activity?.also {
            val inputManager = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            it.currentFocus?.let {
                inputManager?.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }

    protected fun showSoftwareKeyboardPost(editText: EditText) {
        val etReference: EditText? = editText
        editText.post {
            etReference?.also {
                showSoftwareKeyboard(it)
            }
        }
    }

    protected fun showSoftwareKeyboard(editText: EditText) {
        val inputManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputManager!!.toggleSoftInputFromWindow(editText.applicationWindowToken, InputMethodManager.SHOW_IMPLICIT, 0)

    }
}