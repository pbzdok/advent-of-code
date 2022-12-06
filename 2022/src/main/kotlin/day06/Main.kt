package day06

import getResourceAsFile
import java.io.InputStreamReader

const val fileName = "day06_input.txt"

fun main() {
    println("✨Let's start Advent of Code Day 06!✨")

    val file = getResourceAsFile(fileName)!!

    println("🎄Part 1:")
    val signalIndex = file.reader().use { findSignalIndex(4, it) }
    println("📞 The signal marker ends with index $signalIndex")

    println("🎄Part 2:")
    val messageIndex = file.reader().use { findSignalIndex(14, it) }
    println("✉️ The message marker ends with index $messageIndex")
}

fun findSignalIndex(signalSize: Int, reader: InputStreamReader): Int? {
    val buffer = Array(signalSize) { ' ' }
    var index = 0
    var i = reader.read()

    while (i != -1) {
        buffer[index % signalSize] = i.toChar()
        if (index > (signalSize - 1) && buffer.distinct().count() == signalSize) {
            return index + 1
        }
        i = reader.read()
        index++
    }
    return null
}