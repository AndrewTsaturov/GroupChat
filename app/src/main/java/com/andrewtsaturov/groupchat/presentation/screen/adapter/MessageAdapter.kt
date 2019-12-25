package com.andrewtsaturov.groupchat.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrewtsaturov.groupchat.R
import com.andrewtsaturov.groupchat.domain.etnity.ChatMessage

class MessageAdapter: RecyclerView.Adapter<MessageVH>() {
    companion object {
        private const val RECIEVED = 0
        private const val SENDED = 1
    }

    private val items = mutableListOf<ChatMessage>()

    override fun getItemViewType(position: Int): Int {
        if(items.isNotEmpty()){
            if(items[position].fromUser)
                return SENDED
            else
                return RECIEVED
        } else
            return RECIEVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageVH = when (viewType){
        RECIEVED -> MessageRecievedVH(LayoutInflater.from(parent.context).inflate(R.layout.item_message_recieved, parent, false))
        SENDED -> MessageSendedVH(LayoutInflater.from(parent.context).inflate(R.layout.item_message_sended, parent, false))
        else -> MessageRecievedVH(LayoutInflater.from(parent.context).inflate(R.layout.item_message_recieved, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MessageVH, position: Int) = holder.setItem(items[position])

    fun updateData(data: List<ChatMessage>){
        items.clear()
        items.addAll(data)

        notifyDataSetChanged()
    }
}