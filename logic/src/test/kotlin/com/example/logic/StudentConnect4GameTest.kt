package com.example.logic

import com.example.lib.Connect4Game
import org.junit.jupiter.api.Assertions.*

import com.example.testlib.Connect4Test

internal class StudentConnect4GameTest : Connect4Test() {
    override fun createGame(columns: Int, rows: Int): Connect4Game {
        return StudentConnect4Game(columns, rows)
    }
}