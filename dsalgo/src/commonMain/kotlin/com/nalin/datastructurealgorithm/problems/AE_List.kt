package com.nalin.datastructurealgorithm.problems

import com.nalin.datastructurealgorithm.ds.LinkedListNode
import kotlin.math.max
import kotlin.math.min

/**
 * Merge overlapping intervals. They are not likely to be in order
 */
fun mergeOverlappingIntervals(intervals: List<List<Int>>): List<List<Int>> {
    val sortedInterval = intervals.sortedBy {
        it[0]
    }

    val result = mutableListOf<MutableList<Int>>()
    for (item in sortedInterval) {
        if (result.size != 0 && checkIntervalOveralps(result.last(), item)) {
            result.last()[0] = min(item[0], result.last()[0])
            result.last()[1] = max(item[1], result.last()[1])
        } else {
            result.add(mutableListOf(item[0], item[1]))
        }
    }

    return result
}

fun checkIntervalOveralps(item1: List<Int>, item2: List<Int>): Boolean {
    // item1 left is not in item2 right
    // item1 right is not in item2 left
    return !((item1[0] < item2[0] && item1[1] < item2[0])
            || (item1[0] > item2[0] && item1[0] > item2[1]))
}

fun firstDuplicateValue(array: MutableList<Int>): Int {
    val map = hashMapOf<Int, Int>()
    for (item in array) {
        map.put(item, (map.get(item) ?: 0) + 1)
        if (map.get(item)!! > 1) {
            return item
        }
    }
    return -1
}

/**
 * Check if a given linked list is palindrome or not
 */
fun linkedListPalindrome(linkedList: LinkedListNode<Int>?): Boolean {
    fun _traverse(
        head: LinkedListNode<Int>,
        tail: LinkedListNode<Int>
    ): Pair<LinkedListNode<Int>?, Boolean> {
        var result = true
        var newHead: LinkedListNode<Int>? = head
        if (tail.nextNode !== null) {
            val (resHead, newResult) = _traverse(head, tail.nextNode!!)
            newHead = resHead
            result = newResult
        }
        return Pair(newHead?.nextNode, result && newHead?.value == tail.value)
    }

    return _traverse(linkedList!!, linkedList!!).second
}

/**
 * https://www.algoexpert.io/questions/tandem-bicycle
 * Given 2 players and set of speed, find maximum total speed and minimum total speed
 */
fun tandemBicycle(
    redShirtSpeeds: MutableList<Int>,
    blueShirtSpeeds: MutableList<Int>,
    fastest: Boolean
): Int {
    if (redShirtSpeeds.size != blueShirtSpeeds.size) {
        return -1
    }
    redShirtSpeeds.sortDescending()
    blueShirtSpeeds.sortDescending()
    var redPointer = 0
    var bluePointer = if (fastest) redShirtSpeeds.size - 1 else 0
    var totalPoint = 0
    while (redPointer < redShirtSpeeds.size) {
        totalPoint += max(redShirtSpeeds[redPointer], blueShirtSpeeds[bluePointer])
        redPointer++
        bluePointer = if (fastest) bluePointer - 1 else bluePointer + 1
    }
    return totalPoint
}

fun twoNumberSum(array: MutableList<Int>, targetSum: Int): List<Int> {
    val arraySet = mutableSetOf<Int>()
    for (num in array) {
        arraySet.add(num)
    }
    for (num in array) {
        if (num != targetSum - num && arraySet.contains(targetSum - num)) {
            return listOf(targetSum - num, num)
        }
    }
    // Write your code here.
    return listOf<Int>()
}

/**
 * https://www.algoexpert.io/questions/class-photos
 */
fun classPhotos(redShirtHeights: MutableList<Int>, blueShirtHeights: MutableList<Int>): Boolean {
    // Write your code here.
    if (redShirtHeights.size == blueShirtHeights.size) {
        val sortedRed = redShirtHeights.sorted()
        val sortedBlue = blueShirtHeights.sorted()
        var directionSmall: Boolean? = null
        for (i in sortedRed.indices) {

            val diff = sortedRed[i] - sortedBlue[i]
            if (directionSmall == null) {
                directionSmall = diff < 0
            } else if ((directionSmall == true && diff < 0) || directionSmall == false && diff > 0) {
                continue;
            } else {
                return false
            }

        }
        return true
    }
    return false
}

fun getNthFib(n: Int): Double {

    if (n <= 0) return 0.0
    val dp = mutableMapOf<Int, Double>()
    dp[0] = 0.0
    dp[1] = 1.0
    for (i in 2 until n) {
        dp[i] = dp[i - 1]!! + dp[i - 2]!!
    }
    return dp[n - 1]!!


//    fun fib(n: Int, dp: MutableMap<Int, Double>): Double {
//        if (dp.contains(n)) {
//            return dp[n]!!
//        }
//        var output = 0.0;
//        if (n == 0) {
//            output =  0.0
//        }
//        if (n == 1) {
//            output = 1.0
//        }
//
//        if (n >= 2) {
//            output = fib(n - 1, dp) + fib(n - 2, dp)
//            dp[n] = output;
//        }
//
//        return output;
//    }
//    if (n == 0) return 0.0;
//    return fib(n-1, dp)
}



