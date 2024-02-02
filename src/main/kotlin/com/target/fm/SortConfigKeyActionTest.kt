package com.target.fm

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader

class SortConfigKeyActionTest {

    @Test
    fun testSortConfigBlocks() {
        val inputConfig = getResourceFileContent("before.conf")
        val expectedSortedConfig = getResourceFileContent("after.conf")
        val result = sortConfigBlocks(inputConfig)
        println(result)
        assertEquals(expectedSortedConfig, result)
    }

    private fun getResourceFileContent(fileName: String): String {
        val classLoader = javaClass.classLoader
        val resourceAsStream = classLoader.getResourceAsStream(fileName)

        return BufferedReader(InputStreamReader(resourceAsStream)).use { it.readText() }
    }
}
