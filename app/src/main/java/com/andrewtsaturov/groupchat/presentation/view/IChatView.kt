package com.andrewtsaturov.groupchat.presentation.view

import com.andrewtsaturov.groupchat.domain.etnity.ChatMessage
import com.arellomobile.mvp.MvpView

interface IChatView: MvpView {
    fun showMessages(messages: List<ChatMessage>)
}