package com.andrewtsaturov.groupchat.presentation.screen
import android.os.Bundle
import com.andrewtsaturov.groupchat.R
import com.andrewtsaturov.groupchat.presentation.common.OnBackPressed
import com.andrewtsaturov.groupchat.presentation.navigation.LocalCiceroneHolder
import com.andrewtsaturov.groupchat.presentation.navigation.Screens
import com.arellomobile.mvp.MvpAppCompatActivity
import org.koin.android.ext.android.get
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity() {
    private val navigatorHolder = get<LocalCiceroneHolder>().navigationHolder
    private val navigator by lazy { SupportAppNavigator(this, R.id.fragment_container) }
    private val router = get<LocalCiceroneHolder>().router


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.popBackStack()
            router.newRootScreen(Screens.ChatScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragment_container) as? OnBackPressed)?.onBackPressed()
            ?: back()
    }

    fun back(){
        moveTaskToBack(true)
    }

}
