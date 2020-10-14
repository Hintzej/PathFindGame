package com.example.pathfindgame.data

import com.example.pathfindgame.model.Hallway

interface IRoundProvider {
    fun generateNewRound() : List<Hallway>
//    fun checkChoice(hallway : Hallway, guess : Hallway.Guess) : Boolean
}