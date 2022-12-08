package day08

import getResourceAsFile

const val fileName = "day08_input.txt"

fun main() {
    println("✨Let's start Advent of Code Day 08!✨")

    val file = getResourceAsFile(fileName)!!

    val grid = file.useLines { lines ->
        lines.map { line ->
            line.map { it.digitToInt() }
                .toList()
        }.toList()
    }

    println("🎄Part 1:")
    val count = grid.indices.sumOf { r ->
        grid[r].indices.count { c ->
            grid.isTreeVisibleAt(r, c)
        }
    }
    println("🎄There are $count trees visible from the outside of the forest")

    println("🎄Part 2:")
    val score = grid.indices.maxOf { r ->
        grid[r].indices.maxOf { c ->
            grid.treeScoreAt(r, c)
        }
    }
    println("🎄The highest scenic score in the forest is $score")
}


private fun Grid.isTreeVisibleAt(r: Int, c: Int): Boolean {
    val row = this[r]
    val height = this[r][c]
    if (r == 0 || r == row.size - 1 || c == 0 || c == this.size - 1) {
        return true
    }

    val left = row.subList(0, c).max()
    val right = row.subList(c + 1, this.size).max()
    val up = this.map { it[c] }.subList(0, r).max()
    val down = this.map { it[c] }.subList(r + 1, this.size).max()
    return height > left || height > right || height > up || height > down
}

private fun Grid.treeScoreAt(r: Int, c: Int): Int {
    val row = this[r]
    val height = this[r][c]
    if (r == 0 || r == row.size - 1 || c == 0 || c == this.size - 1) {
        return 0
    }

    fun getScore(treeLine: List<Int>): Int {
        var count = 0
        for (t in treeLine) {
            count++
            if (t >= height) {
                break
            }
        }
        return count
    }

    val left = getScore(row.subList(0, c).reversed())
    val right = getScore(row.subList(c + 1, this.size))
    val up = getScore(this.map { it[c] }.subList(0, r).reversed())
    val down = getScore(this.map { it[c] }.subList(r + 1, this.size))

    return left * right * up * down
}

private typealias Grid = List<List<Int>>
