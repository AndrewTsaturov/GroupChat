package com.andrewtsaturov.groupchat.presentation.navigation

import androidx.fragment.app.Fragment
import com.andrewtsaturov.groupchat.presentation.screen.ChatFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens{
    class ChatScreen(): SupportAppScreen(){
        override fun getFragment(): Fragment = ChatFragment()
    }
}