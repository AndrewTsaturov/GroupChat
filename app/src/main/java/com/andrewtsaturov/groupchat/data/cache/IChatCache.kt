package com.andrewtsaturov.groupchat.data.cache

import com.andrewtsaturov.groupchat.data.api.MessageBody
import com.andrewtsaturov.groupchat.domain.etnity.ChatBot
import com.andrewtsaturov.groupchat.domain.etnity.ChatMessage
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface IChatCache {
    fun observeMessages(): Observable<List<ChatMessage>>
    fun addMessage(messageBody: MessageBody): Single<ChatMessage>
    fun getChatBots(): List<ChatBot>

    fun getMessageInput(): String
    fun updateMessageInput(input: String): Single<String>

    fun observeBots(): Observable<List<ChatBot>>
    fun toogleBotEnable(position: Int): Completable
}