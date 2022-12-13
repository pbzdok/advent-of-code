package day09

import getResourceAsFile
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.sqrt

const val fileName = "day09_input.txt"

fun main() {
    println("✨Let's start Advent of Code Day 09!✨")

    val file = getResourceAsFile(fileName)!!

    val visitedTailPositions = mutableSetOf<Vec2>()
    val visitedTailPositions2 = mutableSetOf<Vec2>()

    val shortRope = Rope(2)
    val longRope = Rope(10)

    file.forEachLine { line ->
        val direction = Vec2.fromChar(line[0])
        val n = line.substringAfter(' ').toInt()

        repeat(n) {
            shortRope.move(direction)
            visitedTailPositions.add(shortRope.tail())

            longRope.move(direction)
            visitedTailPositions2.add(longRope.tail())
        }
    }
    println("🪢 The tail of the short rope visited ${visitedTailPositions.size} positions")
    println("🪢 The tail of the short rope visited ${visitedTailPositions2.size} positions")
}

private data class Rope(val length: Int) {
    private val knots = Array(length) { Vec2(0, 0) }

    fun tail(): Vec2 = knots.last()

    fun move(v: Vec2) {
        var head = knots.first()
        head += v
        knots[0] = head
        for (i in 1 until knots.size) {
            val leadingKnot = knots[i - 1]
            var knotToFollow = knots[i]
            val delta = leadingKnot - knotToFollow
            if (delta.length() > 1) {
                knotToFollow += delta.normalize()
                knots[i] = knotToFollow
            }
        }
    }
}

private data class Vec2(
    val x: Int,
    val y: Int
) {

    operator fun plus(other: Vec2) = Vec2(x + other.x, y + other.y)
    operator fun minus(other: Vec2) = Vec2(x - other.x, y - other.y)
    operator fun times(i: Int) = Vec2(x * i, y * i)

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