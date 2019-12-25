package com.andrewtsaturov.groupchat

import com.andrewtsaturov.groupchat.data.api.NotificationSendService
import com.andrewtsaturov.groupchat.data.cache.ChatCache
import com.andrewtsaturov.groupchat.data.cache.IChatCache
import com.andrewtsaturov.groupchat.domain.interactor.ChatInteractor
import com.andrewtsaturov.groupchat.domain.interactor.IChatInteractor
import com.andrewtsaturov.groupchat.domain.repository.ChatRepository
import com.andrewtsaturov.groupchat.domain.repository.IChatRepository
import com.andrewtsaturov.groupchat.presentation.common.ISchedulers
import com.andrewtsaturov.groupchat.presentation.common.Schedulers
import com.andrewtsaturov.groupchat.presentation.navigation.LocalCiceroneHolder
import com.andrewtsaturov.groupchat.presentation.presenter.ChatPresenter
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val app = module {
    single { LocalCiceroneHolder() }
    single {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .client(OkHttpClient())
            .baseUrl("https://fcm.googleapis.com/")
            .build()
    }
    single { Schedulers() as ISchedulers }
    single {get<Retrofit>().create(NotificationSendService::class.java)}
    single {ChatCache() as IChatCache}
    single {ChatRepository(get(), get()) as IChatRepository}
    single {ChatInteractor(get()) as IChatInteractor}

    factory { ChatPresenter(get(), get()) }
}