package com.example.pathfindgame.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

class WinScreenViewModel
    @ViewModelInject constructor() : ViewModel() {
    private val sendChannel = Channel<WinScreenViewModel.Event>()
    val channel : ReceiveChannel<WinScreenViewModel.Event> = sendChannel

    private fun sendEvent(event: WinScreenViewModel.Event) = viewModelScope.launch {
        sendChannel.offer(event)
    }

    fun HomeButtonPress() {
        sendEvent(Event.OnHomeButtonPress())
    }

    sealed class Event {
        class OnHomeButtonPress() : Event()
    }
}