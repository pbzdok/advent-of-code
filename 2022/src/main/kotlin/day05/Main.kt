package day05

import getResourceAsFile
import java.util.Stack

const val fileName = "day05_input.txt"

fun main() {
    println("✨Let's start Advent of Code Day 05!✨")

    val file = getResourceAsFile(fileName)

    println("🎄Part 1:")
    val result = file?.readLines().let { lines ->
        val stacks = parseStacks(lines!!.take(8).toList().reversed())
        lines.drop(10)
            .filter { it.startsWith("move") }
            .map { Move.fromString(it) }
            .forEach {
                it.applyOneByOneTo(stacks)
            }
        stacks.map {
            it.pop()
        }.joinToString("")
    }
    println(result)

    println("🎄Part 2:")
    val result2 = file?.readLines().let { lines ->
        val stacks = parseStacks(lines!!.take(8).toList().reversed())
        lines.drop(10)
            .filter { it.startsWith("move") }
            .map { Move.fromString(it) }
            .forEach {
                it.applyTo(stacks)
            }
        stacks.map {
            it.pop()
        }.joinToString("")
    }
    println(result2)
}

fun parseStacks(lines: Iterable<String>): Array<Stack<Char>> {
    val list: Array<Stack<Char>> = Array(9) { Stack() }
    for (line in lines) {
        for (i in 0..line.length step 4) {
            val stack = list[i / 4]
            val char = line[i + 1]
            if (char != ' ') stack.push(char)
        }
    }
    return list
}

data class Move(
    val n: Int,
    val from: Int,
    val to: Int,
) {
    fun <T : Any> applyOneByOneTo(stacks: Array<Stack<T>>) {
        for (i in 1..n) {
            val element = stacks[from - 1].pop()
            stacks[to - 1].push(element)
        }
    }

    fun <T : Any> applyTo(stacks: Array<Stack<T>>) {
        val elementsToMove = Stack<T>()
        for (i in 1..n) {
            val element = stacks[from - 1].pop()
            elementsToMove.push(element)
        }
        for (i in 1..n) {
            val element = elementsToMove.pop()
            stacks[to - 1].push(element)
        }
    }

    companion object {
        fun fromString(s: String): Move = s.split(" ").let {
            Move(
                it[1].toInt(),
                it[3].toInt(),
                it[5].toInt()
            )
        }
    }
}