package com.example.lib

interface Connect4Game {
    val columns: Int
    val rows: Int

    /** Returns the state of the game grid at a specified column and row number. */
    fun getToken(column: Int, row: Int): Int

    /** Changes the contents of the game grid at a specified column.
     * @param The column to play the token in
     * @param The player who's token to play
     * @return `true` if a valid move, `false` if not.
     */
    fun playToken(column: Int, player: Int): Boolean
}