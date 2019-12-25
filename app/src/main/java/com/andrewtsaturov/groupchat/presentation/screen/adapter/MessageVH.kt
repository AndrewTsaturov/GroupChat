package com.andrewtsaturov.groupchat.presentation.screen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andrewtsaturov.groupchat.domain.etnity.ChatMessage

abstract class MessageVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun setItem(item: ChatMessage)
}