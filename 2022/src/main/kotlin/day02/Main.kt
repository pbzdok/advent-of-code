package day02

import getResourceAsFile

const val fileName = "day02_input.txt"

fun main() {
    println("✨Let's start Advent of Code Day 02!✨")
    val file = getResourceAsFile(fileName)

    println("🎄Part 1:")
    val score = file?.useLines {
        it.map { s ->
            val opponentSign = Sign.fromChar(s[0])
            val mySign = Sign.fromChar(s[2])
            val result =
                if (mySign.beats() == opponentSign) Result.WIN
                else if (mySign == opponentSign) Result.DRAW
                else Result.LOSS
            result.points + mySign.points
        }.sum()
    }
    println("With the strategy guide I would get a Score of $score!")

    println("🎄Part 2")
    val score2 = file?.useLines {
        it.map { s ->
            val opponentSign = Sign.fromChar(s[0])
            val result = Result.fromChar(s[2])
            val mySign = Sign.forResult(result, opponentSign)
            result.points + mySign.points
        }.sum()
    }
    println("With the correctly decrypted strategy guide I would get a Score of $score2!")
}

enum class Sign(val points: Int) {
    ROCK(1),
    PAPER(2),
    SCISSOR(3);

    fun beats(): Sign = when (this) {
        ROCK -> SCISSOR
        PAPER -> ROCK
        SCISSOR -> PAPER
    }

    companion object {
        fun fromChar(char: Char): Sign = when (char) {
            'A', 'X' -> ROCK
            'B', 'Y' -> PAPER
            'C', 'Z' -> SCISSOR
            else -> throw IllegalArgumentException("Unknown Char")
        }

        fun forResult(result: Result, opponentSign: Sign): Sign = when (result) {
            Result.DRAW -> opponentSign
            Result.LOSS -> opponentSign.beats()
            Result.WIN -> Sign.values().find { it.beats() == opponentSign }!!
        }
    }
}

enum class Result(val points: Int) {
    WIN(6),
    LOSS(0),
    DRAW(3);

    companion object {
        fun fromChar(char: Char): Result = when (char) {
            'X' -> LOSS
            'Y' -> DRAW
            'Z' -> WIN
            else -> throw IllegalArgumentException("Unknown Char")
        }
    }
}