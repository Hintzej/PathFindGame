package com.example.pathfindgame.data

import com.example.pathfindgame.model.Hallway
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoundProviderRand
@Inject constructor() : IRoundProvider {

    override fun generateNewRound(): List<Hallway> {
        val hall1 = setDoors(listOf(0,1,2,3), 2)
        val hall2 = setDoors(listOf(0,1,2,3), 3)
        val hall3 = setDoors(listOf(0,1,2,3), 4)
        val hall1CorrectDoors = hall1[0]
        val hall1Visibility = hall1[1]
        val hall2CorrectDoors = hall2[0]
        val hall2Visibility = hall2[1]
        val hall3CorrectDoors = hall3[0]
        val hall3Visibility = hall3[1]

        val firstHall = Hallway(hall1CorrectDoors[0], hall1CorrectDoors[1], hall1CorrectDoors[2], hall1CorrectDoors[3],
            hall1Visibility[0], hall1Visibility[1], hall1Visibility[2], hall1Visibility[3])
        val secondHall = Hallway(hall2CorrectDoors[0], hall2CorrectDoors[1], hall2CorrectDoors[2], hall2CorrectDoors[3],
            hall2Visibility[0], hall2Visibility[1], hall2Visibility[2], hall2Visibility[3])
        val thirdHall = Hallway(hall3CorrectDoors[0], hall3CorrectDoors[1], hall3CorrectDoors[2], hall3CorrectDoors[3],
            hall3Visibility[0], hall3Visibility[1], hall3Visibility[2], hall3Visibility[3])
        return listOf(firstHall, secondHall, thirdHall)
    }

    private fun setDoors(possibleDoors: List<Int>, numDoors: Int): List<List<Boolean>> {
        val visibleDoorNums = possibleDoors.shuffled().take(numDoors)
        // set boolean list for visible doors
        var visibleDoors = mutableListOf(false, false, false, false)
        for (door in visibleDoorNums) {
            visibleDoors[door] = true
        }
        // set boolean list for enterable door(s)
        var correctDoors = mutableListOf(false, false, false, false)
        correctDoors[visibleDoorNums[0]] = true

        return listOf(correctDoors, visibleDoors)

    }

}