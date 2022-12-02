package day01

import java.io.File

const val fileName = "day01_input.txt"

fun main(args: Array<String>) {
    println("Lets start Advent of Code Day 01!")

    val file = getResourceAsFile(fileName)
    val sums = mutableMapOf<Int, Int>()
    var elfIndex = 1

    file?.useLines { lines ->
        lines.forEach {
            if (it.isNotEmpty()) {
                val calories = Integer.valueOf(it)
                sums[elfIndex] = sums[elfIndex]?.plus(calories) ?: calories
            } else elfIndex++
        }
    }

    val mostCalorieElf = sums.maxBy { it.value }
    println("Elf number ${mostCalorieElf.key} has most calories (${mostCalorieElf.value}) with him")

    val topThreeElfSum = sums.values.sortedDescending().take(3).sum()
    println("The three elfs with the most calories have $topThreeElfSum together")
}

fun getResourceAsFile(path: String): File? =
    object {}
        .javaClass
        .classLoader
        .getResource(path)
        ?.path
        ?.let { File(it) }