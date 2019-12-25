package com.andrewtsaturov.groupchat.data.api

import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationSendService {
    @Headers("Authorization: key=AAAAPKmvbn0:APA91bHykhsooMty76RXiMKMXe-Dym3rePDjlcmLVOIO_Ew3wOuFhXq1E78VSAAoSL47rX5R9OAYw6kwtHzXWlo47Id6ea2Z6iwTJatR1x0eWtJeSjSHzL5sq-4PFKWiwFMmOoqMBAKv",
        "Content-Type:application/json")
    @POST("fcm/send")
    fun sendMessage(@Body request: MessageRequest): Completable
}