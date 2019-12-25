package com.andrewtsaturov.groupchat.data.api

import com.google.gson.annotations.SerializedName

class MessageBody(
    @SerializedName("user_id") val userID: Long,
    @SerializedName("user_name") val userName: String,
    @SerializedName("message") val message: String,
    @SerializedName("from_user") val fromUser: Boolean
)