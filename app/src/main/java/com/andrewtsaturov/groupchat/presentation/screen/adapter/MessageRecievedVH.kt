package com.andrewtsaturov.groupchat.presentation.screen.adapter

import android.view.View
import com.andrewtsaturov.groupchat.domain.etnity.ChatMessage
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_message_recieved.view.*

class MessageRecievedVH(itemView: View) : MessageVH(itemView) {
    override fun setItem(item: ChatMessage){
        itemView.apply {
            itemUserImage.apply {
                if(item.userImage != null){
                    visibility = View.VISIBLE
                    setImageBitmap(item.userImage)
                } else if(item.userImageLink != null){
                    visibility = View.VISIBLE
                    Glide.with(itemView).load(item.userImageLink).into(itemUserImage)
                } else {
                    visibility = View.GONE
                }
            }
            itemUserName.text = item.userName
            itemMessage.text = item.message
        }
    }
}