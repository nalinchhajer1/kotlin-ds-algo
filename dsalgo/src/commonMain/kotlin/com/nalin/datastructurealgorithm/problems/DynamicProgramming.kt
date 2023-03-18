package com.nalin.datastructurealgorithm.problems

import kotlin.math.max

/**
 * Some problem solved using dynamic programming or Advance DS
 */

/**
 * Return longest comming subsequence possible between 2 strings
 */
fun longestCommonSubsequence(str1: String, str2: String): String {
    // 1. Prepare a DP table with m+1, n+1
    // 2. Compare 2 char, if equal take diagonal + 1, else take max of left, top value
    // if (a[i] == b[j]) dp[i-1][j-1] + 1 else max(dp[i-1][j],dp[i][j-1]

    val dp = Array(str1.length + 1) { Array(str2.length + 1) { 0 } }
    for (i in 1 until dp.size) {
        for (j in 1 until dp[i].size) {
            dp[i][j] = if (str1[i - 1] == str2[j - 1]) {
                dp[i - 1][j - 1] + 1
            } else {
                max(dp[i - 1][j], dp[i][j - 1])
            }
        }
    }

    // Getting strings from the db table
    var output = ""
    var i = str1.length
    var j = str2.length
    while (i > 0 && j > 0) {
        if (dp[i][j] == dp[i - 1][j]) {
            i--
        } else {
            output = str1[i - 1] + output
            i--
            j--
        }
    }

    return output
}

/**
 * Calculate number of edits require to change fromString to toString. insert, remove, replace
 */
fun editDistance(fromString: String, toString: String): Int {
    //if (dp[i] == dp[j]) dp[i-1][j-1] else min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
    val dp =
        Array(fromString.length + 1) { i -> Array(toString.length + 1) { j -> if (i == 0) j else if (j == 0) i else 0 } }

    for (i in 1 until dp.size) {
        for (j in 1 until dp[i].size) {
            dp[i][j] = if (fromString[i - 1] == toString[j - 1]) {
                dp[i - 1][j - 1]
            } else {
                minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
            }
        }
    }
    return dp.lastOrNull()?.lastOrNull() ?: 0
}

fun longestIncreasingSubsequence(list: List<Int>): Int {
    val dp = Array(list.size) { 0 }
    dp[0] = 1

    fun findElementWithLowerValue(index: Int): Int {
        var i = index - 1
        var maxValue = 0
        while (i > 0) {
            if (list[i] < list[index]) {
                maxValue = max(maxValue, dp[i])
            }
            i--
        }
        println("maxValue $maxValue")
        return maxValue
    }

    for (i in 1 until dp.size) {
        dp[i] = if (list[i - 1] < list[i]) {
            dp[i - 1] + 1
        } else {
            findElementWithLowerValue(i) + 1
        }
    }
    println("dp ${dp.joinToString { it.toString() }}")

    return dp.lastOrNull() ?: 0
}



