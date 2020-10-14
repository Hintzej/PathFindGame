package com.example.pathfindgame.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pathfindgame.data.RoundProviderRand
import com.example.pathfindgame.model.Hallway
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

class HallViewModel
    @ViewModelInject constructor( private val roundProvider: RoundProviderRand
    ) : ViewModel() {
    private val hallways: List<Hallway> = roundProvider.generateNewRound()
    private var currentHallwayNumber  = 0
    var currentHallway: Hallway = hallways[currentHallwayNumber]

    private val sendChannel = Channel<Event>()
    val channel : ReceiveChannel<Event> = sendChannel

    private fun sendEvent(event: Event) = viewModelScope.launch {
        sendChannel.offer(event)
    }

    fun upButtonPressed() {
        if (currentHallway.up) {
            rightChoice()
        } else {
            wrongChoice()
        }
    }

    fun rightButtonPressed() {
        if (currentHallway.right) {
            rightChoice()
        } else {
            wrongChoice()
        }
    }

    fun downButtonPressed() {
        if (currentHallway.down) {
            rightChoice()
        } else {
            wrongChoice()
        }
    }

    fun leftButtonPressed() {
        if (currentHallway.left) {
            rightChoice()
        } else {
            wrongChoice()
        }
    }

    private fun rightChoice() {
        currentHallwayNumber++
        if (currentHallwayNumber >= hallways.size) {
            sendEvent(Event.WonGame()) //finished game
        } else {
            currentHallway = hallways[currentHallwayNumber]
            sendEvent(Event.NextHall()) //advances to next room
        }
    }

    private fun wrongChoice() {
        currentHallwayNumber = 0
        currentHallway = hallways[currentHallwayNumber]
        sendEvent(Event.WrongChoice())
    }

    sealed class Event {
        class WonGame() : Event()
        class NextHall() : Event()
        class WrongChoice() : Event()
    }
}