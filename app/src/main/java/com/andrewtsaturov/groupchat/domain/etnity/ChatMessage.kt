package com.andrewtsaturov.groupchat.domain.etnity

import android.graphics.Bitmap

class ChatMessage(
    val userName: String,
    val message: String,
    val fromUser: Boolean,
    val userImage: Bitmap? = null,
    val userImageLink: String?
)