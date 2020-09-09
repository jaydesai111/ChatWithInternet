package com.jay.chatwithinternet.ui.chatscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jay.chatwithinternet.data.SearchResponse
import com.jay.chatwithinternet.databinding.ItemMessageReceivedBinding
import com.jay.chatwithinternet.databinding.ItemMessageSentBinding

class ChatAdapter : ListAdapter<SearchResponse, RecyclerView.ViewHolder>(ChatDiffCallBack()) {
    companion object {
        const val leftMessage = 0
        const val rightMessage = 1
    }

    class ChatDiffCallBack : DiffUtil.ItemCallback<SearchResponse>() {
        override fun areItemsTheSame(oldItem: SearchResponse, newItem: SearchResponse): Boolean {
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(oldItem: SearchResponse, newItem: SearchResponse): Boolean {
            return oldItem.text == newItem.text
        }

    }

    override fun getItemViewType(position: Int): Int {

        if (getItem(position).isSender) {
            return rightMessage
        }
        return leftMessage

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            leftMessage -> LeftViewHolder.from(parent)
            rightMessage -> RightViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LeftViewHolder -> holder.leftBind(getItem(position))
            is RightViewHolder -> holder.rightBind(getItem(position))
        }
    }

    class LeftViewHolder private constructor(val binding: ItemMessageReceivedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun leftBind(searchResponse: SearchResponse) {
            binding.messageReceived = searchResponse
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMessageReceivedBinding.inflate(layoutInflater, parent, false)
                return LeftViewHolder(binding)
            }
        }
    }

    class RightViewHolder private constructor(val binding: ItemMessageSentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun rightBind(searchResponse: SearchResponse) {
            binding.messageSent = searchResponse
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMessageSentBinding.inflate(layoutInflater, parent, false)
                return RightViewHolder(binding)
            }
        }
    }

    override fun submitList(list: MutableList<SearchResponse>?) {
        super.submitList(list?.let { ArrayList(it) })
    }
}