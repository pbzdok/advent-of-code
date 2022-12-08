package day07

import getResourceAsFile

const val fileName = "day07_input.txt"

fun main() {
    println("✨Let's start Advent of Code Day 07!✨")

    val file = getResourceAsFile(fileName)!!

    val tree = file.useLines { lines ->
        val root = TreeNode("/")
        var node = root
        lines.drop(1).forEach { line ->
            val name = line.substringAfterLast(" ")

            if (line.startsWith("$ cd")) {
                node = node.getNode(name) ?: throw NoSuchElementException(name)
            } else if (line.startsWith("$ ls")) {
                // do nothing
            } else if (line.startsWith("dir")) {
                val child = TreeNode(name, 0, node)
                node.add(child)
            } else {
                val size = line.substringBefore(" ").toInt()
                val child = TreeNode(name, size, node)
                node.add(child)
            }
        }
        root
    }

    val p1 = tree.find { it.totalSize() <= 100_000 }.sumOf { it.totalSize() }
    println(p1)
}

class TreeNode(
    val name: String,
    val size: Int = 0,
    val parent: TreeNode? = null,
) {
    private val children = mutableListOf<TreeNode>()

    fun add(child: TreeNode) = children.add(child)

    fun getNode(name: String): TreeNode? {
        return if (name == "..") parent
        else children.find { it.name == name }
    }

    fun totalSize(): Int {
        return if (children.isEmpty()) return size
        else children.sumOf { it.totalSize() }
    }

    fun find(predicate: (TreeNode) -> Boolean): List<TreeNode> {
        return children.filter(predicate) +
                children.flatMap { it.find(predicate) }
    }
}