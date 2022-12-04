package day03

import getResourceAsFile

const val fileName = "day03_input.txt"

fun main() {
    println("✨Let's start Advent of Code Day 03!✨")

    val file = getResourceAsFile(fileName)

    println("🎄Part 1:")
    val result = file?.useLines { lines ->
        lines.map {
            val half1Items = it.subSequence(0, it.length / 2).toSet()
            val half2Items = it.subSequence(it.length / 2, it.length).toSet()
            val matchingItem = (half1Items intersect half2Items).first()
            priorityMap[matchingItem]!!
        }.sum()
    }
    println("The sum of priorities of all duplicate items equals $result")

    println("🎄Part 2:")
    val result2 = file?.useLines { lines ->
        lines.chunked(3)
            .map { chunk ->
                chunk.map { it.toSet() }
                    .reduce { acc, chars -> acc intersect chars }
                    .first()
            }
            .map { priorityMap[it]!! }
            .sum()
    }
    println("The sum of priorities of groups of three is $result2")
}

val priorityMap = (('a'..'z') + ('A'..'Z'))
    .withIndex()
    .associate { it.value to it.index + 1 }