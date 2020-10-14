package com.example.pathfindgame.data

import com.example.pathfindgame.model.Hallway
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoundProvider
    @Inject constructor() : IRoundProvider {

    private val hall1Doors = listOf(true,false,false,false)
    private val hall1Visibility = listOf(true,false,true,false)
    private val hall2Doors = listOf(false,true,false,false)
    private val hall2Visibility = listOf(false,true,false,true)

    override fun generateNewRound(): List<Hallway> {
        val firstHall = Hallway(hall1Doors[0], hall1Doors[1], hall1Doors[2], hall1Doors[3],
            hall1Visibility[0], hall1Visibility[1], hall1Visibility[2], hall1Visibility[3])
        val secondHall = Hallway(hall2Doors[0], hall2Doors[1], hall2Doors[2], hall2Doors[3],
            hall2Visibility[0], hall2Visibility[1], hall2Visibility[2], hall2Visibility[3])
        return listOf(firstHall, secondHall)
    }

}