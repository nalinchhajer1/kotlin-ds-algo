package com.nalin.datastructurealgorithm.problem

import com.nalin.datastructurealgorithm.ds.*
import com.nalin.datastructurealgorithm.problems.binarySearch
import com.nalin.datastructurealgorithm.problems.findFibonacciSeries
import com.nalin.datastructurealgorithm.problems.primeNumbers
import kotlin.test.Test
import kotlin.test.assertEquals

class DataStructureTest {

    @Test
    fun testFibSeries() {
        assertEquals(findFibonacciSeries(10), listOf<Int>(0, 1, 1, 2, 3, 5, 8, 13, 21, 34))
    }

    @Test
    fun testPrimeNumber() {
        assertEquals(primeNumbers(10000).size, 1229)
    }

    @Test
    fun testBinarySearch() {
        assertEquals(binarySearch(listOf(1), 10), false);
        assertEquals(binarySearch(listOf(1,2,3,4,10), 10), true);
        assertEquals(binarySearch(listOf(10,12,13,14), 10), true);
        assertEquals(binarySearch(listOf(1,2,10,14), 10), true);
    }


}