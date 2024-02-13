package com.configsorter

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader

class SortConfigKeyActionTest {

    @Test
    fun testSortConfigBlocks2() {
        val inputConfig = getResourceFileContent("before2.conf")
        val expectedSortedConfig = getResourceFileContent("after2.conf")
        val result = sortConfigBlocks(inputConfig)
        assertEquals(expectedSortedConfig, result)
    }

    @Test
    fun testSortConfigBlocks() {
        val inputConfig = getResourceFileContent("before.conf")
        val expectedSortedConfig = getResourceFileContent("after.conf")
        val result = sortConfigBlocks(inputConfig)
        assertEquals(expectedSortedConfig, result)
    }

    @Test
    fun testSortConfigBlocks3() {
        val inputConfig = getResourceFileContent("before3.conf")
        
        println(
            sortConfigBlocks(inputConfig),
        )
    }

    private fun getResourceFileContent(fileName: String): String {
        val classLoader = javaClass.classLoader
        val resourceAsStream = classLoader.getResourceAsStream(fileName)

        return BufferedReader(InputStreamReader(resourceAsStream)).use { it.readText() }
    }
}
