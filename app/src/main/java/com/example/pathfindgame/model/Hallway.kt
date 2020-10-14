package com.example.pathfindgame.model

data class Hallway (val up: Boolean = false,
                    val right: Boolean = false,
                    val down: Boolean = false,
                    val left: Boolean = false,
                    val upVisibility : Boolean = true,
                    val rightVisibility : Boolean = true,
                    val downVisibility : Boolean = true,
                    val leftVisibility : Boolean = true)