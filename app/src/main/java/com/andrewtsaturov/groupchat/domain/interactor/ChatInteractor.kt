package com.andrewtsaturov.groupchat.domain.interactor

import com.andrewtsaturov.groupchat.data.api.MessageBody
import com.andrewtsaturov.groupchat.domain.etnity.ChatBot
import com.andrewtsaturov.groupchat.domain.etnity.ChatMessage
import com.andrewtsaturov.groupchat.domain.repository.IChatRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class ChatInteractor(private val chatRepository: IChatRepository): IChatInteractor {
    override fun observeBots(): Observable<List<ChatBot>> = chatRepository.observeBots()

    override fun setBotEnable(position: Int): Completable = chatRepository.setBotEnable(position)

    override fun observeMessages(): Observable<List<ChatMessage>> = chatRepository.observeMessages()

    override fun addMessage(messageBody: MessageBody): Single<ChatMessage> = chatRepository.addMessage(messageBody)

    override fun updateInputMessage(input: String): Single<String> = chatRepository.updateInputMessage(input)

    override fun sendMessage(fromUser: Boolean): Completable = chatRepository.sendMessage(fromUser)
}