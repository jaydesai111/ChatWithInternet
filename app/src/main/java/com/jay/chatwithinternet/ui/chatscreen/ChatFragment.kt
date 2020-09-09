package com.jay.chatwithinternet.ui.chatscreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jay.chatwithinternet.R
import com.jay.chatwithinternet.data.SearchResponse
import com.jay.chatwithinternet.databinding.FragmentChatBinding
import com.jay.chatwithinternet.ui.chatscreen.adapter.ChatAdapter
import com.jay.chatwithinternet.utils.hideKeyboard
import com.jay.chatwithinternet.utils.toast
import dagger.android.support.AndroidSupportInjection
import java.util.*
import javax.inject.Inject


class ChatFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var fragmentChatBinding: FragmentChatBinding

    private lateinit var viewModel: ChatViewModel

    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentChatBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory)[ChatViewModel::class.java]

        fragmentChatBinding.chatViewModel = viewModel
        fragmentChatBinding.lifecycleOwner = this

        chatAdapter = ChatAdapter()
        fragmentChatBinding.reyclerviewMessageList.adapter = chatAdapter

        //Observing answer from view model, Once we get response from server we will update our recyclerview
        viewModel._answerMetaData.observe(viewLifecycleOwner, Observer {
            Log.i("ChatFrgment", "this is value of " + it)
            chatAdapter.submitList(it)
            scrollToBottom()
        })

        viewModel.loadError.observe(viewLifecycleOwner, Observer {
            toast(it)
        })

        //onClick send button, send question form edittextChatbox to server
        fragmentChatBinding.buttonChatboxSend.setOnClickListener {

            if(fragmentChatBinding.edittextChatbox.text.toString().isNullOrEmpty())
            {
                return@setOnClickListener
            }
            val searchResponse =
                SearchResponse(fragmentChatBinding.edittextChatbox.text.toString(), true)
            viewModel.addQuestion(searchResponse)
            fragmentChatBinding.edittextChatbox.hideKeyboard()
        }

        fragmentChatBinding.ibBack.setOnClickListener {
            activity?.finish()
        }

        return fragmentChatBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    //For scrolling chat to bottom when user enter message or will get response from server
    private fun scrollToBottom() {
        fragmentChatBinding.reyclerviewMessageList.postDelayed(object : Runnable {
            override fun run() {
                fragmentChatBinding.reyclerviewMessageList.smoothScrollToPosition(
                    fragmentChatBinding.reyclerviewMessageList.adapter?.itemCount!! - 1
                )
            }
        }, 500)
    }


}