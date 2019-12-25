package com.andrewtsaturov.groupchat.domain.etnity

import android.graphics.Bitmap

class ChatBot(
    val id: Long,
    val username: String,
    var isActive: Boolean = false,
    val avatar: Bitmap? = null,
    val avatarLink: String? = null
    ){

    fun generateAnswer(inputMessage: String): String{
        //Here will be a method that responding the client speech
        //I have tryed find something ui library but i can't find it sorry
        return "Hello, my name is $username.I only learh how to speack wiht humas"
    }
}