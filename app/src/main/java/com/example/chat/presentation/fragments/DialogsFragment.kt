package com.example.chat.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat.R
import com.example.chat.databinding.FragmentDialogsBinding
import com.example.chat.presentation.models.Chat
import com.example.chat.presentation.recyclerview.ChatAdapter
import com.example.chat.presentation.recyclerview.ChatListener
import com.example.chat.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogsFragment : Fragment(), ChatListener {
    private val viewModel: MainViewModel by activityViewModels()
    private val adapter: ChatAdapter by lazy { ChatAdapter(this) }

    private var _binding: FragmentDialogsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDialogsBinding.inflate(inflater, container, false)
        //viewModel.loadMessages()
        //viewModel.loadChats()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDialogs.adapter = adapter
        binding.rvDialogs.layoutManager = LinearLayoutManager(context)
        viewModel.chats.observe(viewLifecycleOwner) {
            Log.d(TAG, "LiveData observe: $it")
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOpenChat(chat: Chat) {
        Log.d(TAG, "Should open chat")
        //TODO("Not yet implemented")
    }

    companion object {
        const val TAG = "DialogsFragment"
    }
}