package day11

import getResourceAsFile
import java.util.Stack

const val fileName = "day11_input.txt"

fun main() {
    println("✨Let's start Advent of Code Day 11!✨")

    val file = getResourceAsFile(fileName)!!

    val lines = file.readLines()
    val monkeys = mutableListOf<Monkey>()

    for (i in lines.indices step 8) {
        if (lines[i].startsWith("Monkey")) {
            val items = collectItems(lines[i + 1])
            val operationParams = collectOperationParams(lines[i + 2])

            val operation: (Int) -> Int = { x ->
                val p1 = operationParams[0].let {
                    if (it == "old") x
                    else it.toInt()
                }
                val p2 = operationParams[2].let {
                    if (it == "old") x
                    else it.toInt()
                }
                val operator = operationParams[1].toOperation()
                operator(p1, p2)
            }

            val divisor = lines[i + 3].substringAfterLast(" ").toInt()
            val test: (Int) -> Boolean = { it % divisor == 0 }

            monkeys.add(
                Monkey(
                    items = Stack<Int>().also { it.addAll(items) },
                    operation = operation,
                    test = test
                )
            )
        }
    }
}

fun collectItems(s: String): Stack<Int> {
    val stack = Stack<Int>()
    val items = s
        .substringAfter(":")
        .split(",")
        .map { it.trim() }
        .map { it.toInt() }
    stack.addAll(items)
    return stack
}

fun collectOperationParams(s: String): List<String> {
    return s.substringAfter("=")
        .split(" ")
        .map { it.trim() }
}

private class Monkey(
    private val items: Stack<Int>,
    private val operation: (Int) -> Int,
    private val test: (Int) -> Boolean
) {
    fun throwItem(index: Int) = items.pop()

    fun catch(item: Int) = items.add(item)
}

private fun String.toOperation(): (a: Int, b: Int) -> Int {
    return when (this) {
        "*" -> { a: Int, b: Int -> a * b }
        "+" -> { a: Int, b: Int -> a + b }
        else -> throw IllegalArgumentException()
    }
}