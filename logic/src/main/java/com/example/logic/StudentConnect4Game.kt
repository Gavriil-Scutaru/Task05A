package com.example.logic

import com.example.lib.Connect4Game

class StudentConnect4Game (
    override val columns: Int = 7,
    override val rows: Int = 10
): Connect4Game {

    override fun getToken(column: Int, row: Int): Int {
        TODO("Not yet implemented")
    }

    override fun playToken(column: Int, player: Int): Boolean {
        TODO("Not yet implemented")
    }
}

