package com.configsorter

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
                stack.add(config.substring(keyStart, i).trim())
                stack.add("{")
                blockStart = i
                keyStart = i + 1
            }
            '}' -> {
                val strings = mutableListOf<String>()

                while (stack.isNotEmpty()) {
                    val v = stack.pop()
                    if (v == "{") {
                        key = stack.pop()
                        stack.add("$key {${strings.sorted().joinToString(separator = "\n\t", prefix = "\n\t")}\n}\n")
                        break
                    } else {
                        strings.add(v.replace("\t","\t\t").replace("}\n","\t}"))
                    }
                }
                blockStart = i + 1
                keyStart = i + 1
            }
            '\n' -> {
                if (blockStart + 1 < i) {
                    stack.add(config.substring(blockStart + 1, i).trim())
                }
                blockStart = i
                keyStart = i
            }
        }
    }

    return stack.toList()
}
