package com.example.testlib

import com.example.lib.Connect4Game
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

abstract class Connect4Test {
    lateinit var defaultGame: Connect4Game

    abstract fun createGame(columns: Int, rows: Int): Connect4Game

    @BeforeEach
    fun createTestGrid() {
        defaultGame = createGame(8, 10)
    }

    @Test
    fun testGetToken() {
        for(col in 0 until defaultGame.columns) {
            for (row in 0 until defaultGame.rows) {
                val token = defaultGame.getToken(col, row)
                assertEquals(0, token)
            }
        }
    }

    @Test
    fun testPlayToken() {
        defaultGame.playToken(0, 1)
        assertEquals(1, defaultGame.getToken(0, 0))
        assertEquals(0, defaultGame.getToken(0, 1))

        defaultGame.playToken(1, 2)
        assertEquals(2, defaultGame.getToken(1, 0))
        assertEquals(0, defaultGame.getToken(2, 0))

        defaultGame.playToken(0, 1)
        assertEquals(1, defaultGame.getToken(0,1))
        assertEquals(1, defaultGame.getToken(0,0))
        assertEquals(0, defaultGame.getToken(0,2))

        defaultGame.playToken(0, 2)
        assertEquals(2, defaultGame.getToken(0,2))
        assertEquals(1, defaultGame.getToken(0,1))
        assertEquals(1, defaultGame.getToken(0,0))
        assertEquals(0, defaultGame.getToken(0,3))
    }


}