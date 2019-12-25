package com.andrewtsaturov.groupchat.presentation.navigation

import ru.terrakok.cicerone.Cicerone

class LocalCiceroneHolder {
    val cicerone = Cicerone.create()
    val navigationHolder = cicerone.navigatorHolder
    val router = cicerone.router
}