package com.andrewtsaturov.groupchat.data.api

import com.google.gson.annotations.SerializedName

class MessageRequest(
    @SerializedName("data") val notificationBody: MessageBody,
    @SerializedName("/topics/topic_name") val topic: String = "chat"
)