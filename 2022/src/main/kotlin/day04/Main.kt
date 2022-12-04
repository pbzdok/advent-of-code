package day04

import getResourceAsFile

const val fileName = "day04_input.txt"

fun main() {
    println("Lets start Advent of Code Day 04!")

    val file = getResourceAsFile(fileName)

    println("Part 1:")
    val result = file?.useLines { lines ->
        lines.map {
            val firstSection = parseRange(it.substringBefore(","))
            val secondSection = parseRange(it.substringAfter(","))
            val common = firstSection intersect secondSection
            common.size == firstSection.size || common.size == secondSection.size
        }
            .filter { it }
            .count()
    }
    println("There are $result pairs containing each other completely.")

    println("Part 2:")
    val result2 = file?.useLines { lines ->
        lines.map {
            val firstSection = parseRange(it.substringBefore(","))
            val secondSection = parseRange(it.substringAfter(","))
            val common = firstSection intersect secondSection
            common.isNotEmpty()
        }
            .filter { it }
            .count()
    }
    println("There are $result2 pairs containing each other at least on one section.")
}

fun parseRange(range: String): Set<Int> {
    val first = range.substringBefore("-").toInt()
    val second = range.substringAfter("-").toInt()
    return (first..second).toSet()
}