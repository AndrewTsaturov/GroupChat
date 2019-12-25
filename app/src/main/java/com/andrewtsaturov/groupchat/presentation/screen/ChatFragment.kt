package com.andrewtsaturov.groupchat.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrewtsaturov.groupchat.R
import com.andrewtsaturov.groupchat.domain.etnity.ChatMessage
import com.andrewtsaturov.groupchat.presentation.common.BaseFragment
import com.andrewtsaturov.groupchat.presentation.common.MvpTextWatcher
import com.andrewtsaturov.groupchat.presentation.presenter.ChatPresenter
import com.andrewtsaturov.groupchat.presentation.screen.adapter.MessageAdapter
import com.andrewtsaturov.groupchat.presentation.view.IChatView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_chat.*
import org.koin.android.ext.android.get

class ChatFragment(): BaseFragment(), IChatView{
    override val layoutResource: Int = R.layout.fragment_chat

    @InjectPresenter
    lateinit var presenter: ChatPresenter

    @ProvidePresenter
    fun providePresenter(): ChatPresenter = get()

    private val messageAdapter = MessageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messagesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = messageAdapter
        }

        chatInputField.addTextChangedListener(object : MvpTextWatcher(){
            override fun updateValue(value: CharSequence) {
                presenter.updateMessageInput(value.toString())
            }
        })

        sendMessageButton.setOnClickListener {
            presenter.sendMessage()
        }
    }

    override fun showMessages(messages: List<ChatMessage>) = messageAdapter.updateData(messages)
}