package com.example.chat.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chat.databinding.ChatItemBinding
import com.example.chat.presentation.models.Chat

class ChatViewHolder(
    private val binding: ChatItemBinding,
    private val listener: ChatListener
): ViewHolder(binding.root) {
    fun bind(chat: Chat) {
        binding.tvChatName.text = chat.name
        binding.root.setOnClickListener {
            listener.onOpenChat(chat)
        }
    }
}