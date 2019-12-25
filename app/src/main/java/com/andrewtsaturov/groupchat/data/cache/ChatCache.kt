package com.andrewtsaturov.groupchat.data.cache

import com.andrewtsaturov.groupchat.data.api.MessageBody
import com.andrewtsaturov.groupchat.domain.etnity.ChatBot
import com.andrewtsaturov.groupchat.domain.etnity.ChatMessage
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class ChatCache: IChatCache {
    private val messagesRelay = BehaviorRelay.create<List<ChatMessage>>()
    private val messageInputRelay = BehaviorRelay.create<String>()
    private val botsRelay = BehaviorRelay.create<List<ChatBot>>()

    init {
        val bots = mutableListOf<ChatBot>()
        bots.add(ChatBot(1, "John Doe", true, null, "https://sun9-69.userapi.com/c856020/v856020619/176143/kg96A23z_aA.jpg"))
        bots.add(ChatBot(2, "John Smith", false, null, "https://sun9-43.userapi.com/c855420/v855420332/16c2ce/GNufIUUdqgg.jpg"))
        bots.add(ChatBot(3, "Helena Bonem", false, null, "https://sun9-67.userapi.com/c831109/v831109165/90828/_OlP8UHeSBA.jpg"))
    }


    override fun observeMessages(): Observable<List<ChatMessage>> = messagesRelay.hide()

    override fun getChatBots(): List<ChatBot> = botsRelay.value!!

    override fun addMessage(messageBody: MessageBody): Single<ChatMessage> = Single.fromCallable {
        val message = ChatMessage(messageBody.userName, messageBody.message, messageBody.fromUser, null,
            getUserImageLink(messageBody.userID, botsRelay.value!!))

        val messages = mutableListOf<ChatMessage>()
        if(messagesRelay.value != null)
            messages.addAll(messagesRelay.value!!)
        messages.add(message)

        message
    }

    override fun getMessageInput(): String = messageInputRelay.value?: ""

    override fun updateMessageInput(input: String): Single<String> = Single.fromCallable {
        messageInputRelay.accept(input)
        input
    }

    override fun observeBots(): Observable<List<ChatBot>> = botsRelay.hide()

    override fun toogleBotEnable(position: Int): Completable = Completable.create {
        val bots = botsRelay.value!!
        bots[position].isActive = !bots[position].isActive

        botsRelay.accept(bots)
    }

    private fun getUserImageLink(userID: Long, chatBots: List<ChatBot>): String {
        var result = ""
        chatBots.forEach {
            if(userID == it.id) result = it.avatarLink?: ""
        }

        return result
    }
}