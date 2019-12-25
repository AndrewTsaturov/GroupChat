package com.andrewtsaturov.groupchat.domain.repository

import com.andrewtsaturov.groupchat.data.api.MessageBody
import com.andrewtsaturov.groupchat.data.api.MessageRequest
import com.andrewtsaturov.groupchat.data.api.NotificationSendService
import com.andrewtsaturov.groupchat.data.cache.IChatCache
import com.andrewtsaturov.groupchat.domain.etnity.ChatBot
import com.andrewtsaturov.groupchat.domain.etnity.ChatMessage
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.thread
import kotlin.random.Random

class ChatRepository(
    private val chatCache: IChatCache,
    private val notificationSendService: NotificationSendService
): IChatRepository {
    override fun observeBots(): Observable<List<ChatBot>> = chatCache.observeBots()

    override fun setBotEnable(position: Int): Completable = chatCache.toogleBotEnable(position)

    override fun observeMessages(): Observable<List<ChatMessage>> = chatCache.observeMessages()

    override fun addMessage(messageBody: MessageBody): Single<ChatMessage> = chatCache.addMessage(messageBody)

    override fun updateInputMessage(input: String): Single<String> = chatCache.updateMessageInput(input)

    override fun sendMessage(fromUser: Boolean): Completable = Completable.create {
        if(fromUser)
            notificationSendService.sendMessage(MessageRequest(MessageBody(0, "AndroidUser", chatCache.getMessageInput(), fromUser)))
        else{
            chatCache.getChatBots().forEach {
                if(it.isActive){
                    Timer("delay", false).schedule(Random.nextLong(1000, 5000)){
                        notificationSendService.sendMessage(MessageRequest(MessageBody(it.id, it.username, it.generateAnswer(chatCache.getMessageInput()), fromUser)))
                    }
                }
            }
        }
    }

}