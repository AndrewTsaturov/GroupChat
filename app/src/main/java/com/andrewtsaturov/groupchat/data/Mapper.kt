package com.andrewtsaturov.groupchat.data

import com.andrewtsaturov.groupchat.data.api.MessageBody
import com.andrewtsaturov.groupchat.data.api.MessageRequest
import com.andrewtsaturov.groupchat.domain.etnity.ChatBot

fun ChatBot.createMessage(message: String, fromUser: Boolean) = MessageRequest(MessageBody(id, username, message, fromUser))

