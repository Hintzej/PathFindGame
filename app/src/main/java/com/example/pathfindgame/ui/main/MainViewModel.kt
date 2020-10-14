package com.example.pathfindgame.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

class MainViewModel
    @ViewModelInject constructor(
    ) : ViewModel() {

    private val sendChannel = Channel<Event>()
    val channel : ReceiveChannel<Event> = sendChannel

    private fun sendEvent(event: Event) = viewModelScope.launch {
        sendChannel.offer(event)
    }

    fun onStartButtonClick() {
        sendEvent(Event.NavigateToHallways())
    }

    sealed class Event {
        class NavigateToHallways() : Event()
    }
}