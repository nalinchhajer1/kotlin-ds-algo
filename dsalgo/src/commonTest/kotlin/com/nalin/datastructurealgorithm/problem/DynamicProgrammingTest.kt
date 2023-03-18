package com.nalin.datastructurealgorithm.problem

import com.nalin.datastructurealgorithm.problems.editDistance
import com.nalin.datastructurealgorithm.problems.longestCommonSubsequence
import com.nalin.datastructurealgorithm.problems.longestIncreasingSubsequence
import kotlin.test.Test
import kotlin.test.assertEquals

class DynamicProgrammingTest {

    @Test
    fun testLongestCommonSubsequence() {
        assertEquals(longestCommonSubsequence("abcdgh", "aedfhr"), "adh")
        assertEquals(longestCommonSubsequence("ababcabcd", "abcdef"), "abcd")
        assertEquals(longestCommonSubsequence("", ""), "")
        assertEquals(longestCommonSubsequence("a", "b"), "")
    }

    @Test
    fun testeditDistance() {
        assertEquals(editDistance("abc", "dc"), 2)
        assertEquals(editDistance("abc", "abc"), 0)
        assertEquals(editDistance("geek", "gesek"), 1)
    }

    @Test
    fun testlongestIncreasingSubsequence() {
        assertEquals(longestIncreasingSubsequence(listOf(10, 21, 20, 22, 23)), 3)
        assertEquals(longestIncreasingSubsequence(listOf(23, 22, 21, 20)), 1)
        assertEquals(longestIncreasingSubsequence(listOf(3, 10, 2, 1, 20)), 3)
        assertEquals(longestIncreasingSubsequence(listOf(50, 3, 10, 7, 40, 80)), 4)
    }
}