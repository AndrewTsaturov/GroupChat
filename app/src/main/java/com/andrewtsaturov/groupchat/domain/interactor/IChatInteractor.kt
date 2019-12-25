package com.andrewtsaturov.groupchat.domain.interactor

import com.andrewtsaturov.groupchat.data.api.MessageBody
import com.andrewtsaturov.groupchat.domain.etnity.ChatBot
import com.andrewtsaturov.groupchat.domain.etnity.ChatMessage
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface IChatInteractor {
    fun observeBots(): Observable<List<ChatBot>>
    fun setBotEnable(position: Int): Completable
    fun observeMessages(): Observable<List<ChatMessage>>
    fun addMessage(messageBody: MessageBody): Single<ChatMessage>
    fun updateInputMessage(input: String): Single<String>
    fun sendMessage(fromUser: Boolean): Completable
}