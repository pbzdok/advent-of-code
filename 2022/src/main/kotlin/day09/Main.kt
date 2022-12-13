package day09

import getResourceAsFile
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.sqrt

const val fileName = "day09_input.txt"

fun main() {
    println("✨Let's start Advent of Code Day 09!✨")

    val file = getResourceAsFile(fileName)!!

    val ropeLength = 1
    val visitedTailPositions = mutableSetOf<Vec2>()
    val start = Vec2(0, 0)
    var head = start
    var tail = start.also { visitedTailPositions.add(it) }

    file.forEachLine { line ->
        val direction = Vec2.fromChar(line[0])
        val n = line[2].digitToInt()

        repeat(n) {
            head += direction
            val th = head - tail
            if (th.length() > ropeLength) {
                tail += th.scale(ropeLength)
                visitedTailPositions.add(tail)
            }
        }
    }

    println(visitedTailPositions.size)
}

private data class Vec2(
    val x: Int,
    val y: Int
) {

    operator fun plus(other: Vec2) = Vec2(x + other.x, y + other.y)
    operator fun minus(other: Vec2) = Vec2(x - other.x, y - other.y)
    operator fun times(i: Int) = Vec2(x * i, y * i)

    fun scale(i: Int): Vec2 = normalize() * i

    fun normalize(): Vec2 {
        return Vec2(
            x.coerceIn(-1..1),
            y.coerceIn(-1..1)
        )
    }

    fun length(): Int {
        return if (y == 0) abs(x)
        else if (x == 0) abs(y)
        else sqrt(((x * x) + (y * y)).toDouble()).roundToInt()
    }

    companion object {
        fun fromChar(c: Char): Vec2 = when (c) {
            'U' -> Vec2(0, 1)
            'D' -> Vec2(0, -1)
            'L' -> Vec2(-1, 0)
            'R' -> Vec2(1, 0)
            else -> throw IllegalArgumentException("Cannot parse direction, unknown char $c")
        }
    }
}