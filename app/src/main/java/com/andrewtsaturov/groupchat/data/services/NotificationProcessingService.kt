package com.andrewtsaturov.groupchat.data.services

import android.util.Log
import com.andrewtsaturov.groupchat.data.api.MessageBody
import com.andrewtsaturov.groupchat.domain.interactor.IChatInteractor
import com.andrewtsaturov.groupchat.presentation.common.ISchedulers
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import org.json.JSONObject
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent



class NotificationProcessingService : FirebaseMessagingService(), KoinComponent {
    val chatInteractor: IChatInteractor by inject()
    val schedulers: ISchedulers by inject()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            Log.d("MESSAGE_BODY",  remoteMessage.notification!!.body?: "no_body")

            processNotificationBody(remoteMessage.data)
        }
    }

    private fun processNotificationBody(data: Map<String, String>?) {
        if(data != null){
            val json = JSONObject(data)
            val messageBody = Gson().fromJson(json.toString(), MessageBody::class.java)
            chatInteractor.addMessage(messageBody).subscribeOn(schedulers.io())

            if(messageBody.fromUser){
                chatInteractor.sendMessage(false).subscribeOn(schedulers.io())
            }
        }
    }
}
