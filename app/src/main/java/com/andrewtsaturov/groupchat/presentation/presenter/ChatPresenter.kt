package com.andrewtsaturov.groupchat.presentation.presenter

import android.util.Log
import com.andrewtsaturov.groupchat.domain.interactor.IChatInteractor
import com.andrewtsaturov.groupchat.presentation.common.ISchedulers
import com.andrewtsaturov.groupchat.presentation.view.IChatView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign

@InjectViewState
class ChatPresenter(
    private val chatInteractor: IChatInteractor,
    private val schedulers: ISchedulers
): MvpPresenter<IChatView>() {
    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        chatInteractor
            .observeMessages()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                viewState.showMessages(it)
            }, {
                it.printStackTrace()
            }).untilDestroy()
    }

    fun updateMessageInput(message: String) {
        chatInteractor.updateInputMessage(message)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({

            }, {
                it.printStackTrace()
            }).untilDestroy()
    }

    fun sendMessage() {
        chatInteractor.sendMessage(true)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                Log.d("sended", "message")
            }, {
                it.printStackTrace()
            }).untilDestroy()
    }

    fun Disposable.untilDestroy() {
        disposable += this
    }
}