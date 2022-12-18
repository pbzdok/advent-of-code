package day10

import getResourceAsFile

const val fileName = "day10_input.txt"

fun main() {
    println("✨Let's start Advent of Code Day 10!✨")

    val file = getResourceAsFile(fileName)!!

    val cpu = CPU()
    val relevantCycles = intArrayOf(20, 60, 100, 140, 180, 220)
    val record = mutableMapOf<Int, Int>()

    file.useLines {
        it.forEach { line ->
            val op = line.substringBefore(' ')
            val v = line.substringAfter(' ', "")
                .takeIf { it.isNotEmpty() }
                ?.toInt()

            record[cpu.cycle] = cpu.x
            when (op) {
                "addx" -> {
                    cpu.cycle++
                    record[cpu.cycle] = cpu.x
                    cpu.cycle++
                    record[cpu.cycle] = cpu.x
                    cpu.addX(v!!)
                }

                "noop" -> {
                    cpu.cycle++
                    record[cpu.cycle] = cpu.x
                    cpu.noop()
                }

                else -> throw IllegalArgumentException()
            }
            record[cpu.cycle] = cpu.x
        }
    }

    val signalStrengthSum = record
        .filter { relevantCycles.contains(it.key) }
        .map { it.key * it.value }
        .sum()

    println("The sum of the six signal strenghts is $signalStrengthSum")

    var cycle = 1
    for (row in 0..5) {
        for (x in 0..39) {
            val sprite = record[cycle]?.let { it - 1..it + 1 }
            if (sprite != null && x in sprite) print("#")
            else print(" ")
            cycle++
        }
        println()
    }
}


private class CPU(
    var x: Int = 1,
    var cycle: Int = 1
) {
    fun addX(v: Int) {
        x += v
    }

    fun noop() {}
}