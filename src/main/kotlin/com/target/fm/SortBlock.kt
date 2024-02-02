package com.target.fm

import java.util.Stack

fun sortConfigBlocks(config: String): String {
    val blocks = extractConfigBlocks(config)
    return blocks.sorted().joinToString("\n").trim()
}

fun extractConfigBlocks(config: String): List<String> {
    var blockStart = -1
    var keyStart = 0
    var key = ""
    val stack = Stack<String>()
    for (i in config.indices) {
        when (config[i]) {
            '{' -> {
                key = config.substring(keyStart, i).trim()
                stack.add(key)
                stack.add("{")
                blockStart = i
                keyStart = i + 1
            }
            '}' -> {
                val lines = config.substring(blockStart + 1, i).trim().lines()
                val sortedValues = lines.map { it.trim() }.sorted().joinToString("\n\t", "\n\t", "\n")
                val string = mutableListOf(sortedValues)

                while (stack.isNotEmpty()) {
                    val v = stack.pop()
                    if (v == "{") {
                        key = stack.pop()
                        stack.add("$key {${string.joinToString(separator = " ")}}\n")
                        break
                    } else {
                        val s = v.replace("\t", "\t\t").replace("}", "\t}")
                        string.add("\t" + s)
                    }
                }
                blockStart = i + 1
                keyStart = i + 1
            }
        }
    }

    return stack.toList()
}
