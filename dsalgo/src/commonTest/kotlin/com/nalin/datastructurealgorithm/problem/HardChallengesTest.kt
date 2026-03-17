package com.nalin.datastructurealgorithm.problem

import com.nalin.datastructurealgorithm.problems.DynamicConnectivityOperation
import com.nalin.datastructurealgorithm.problems.FlowEdge
import com.nalin.datastructurealgorithm.problems.TreeEdge
import com.nalin.datastructurealgorithm.problems.WeightedEdge
import com.nalin.datastructurealgorithm.problems.alienDictionaryOrder
import com.nalin.datastructurealgorithm.problems.countDistinctSubstrings
import com.nalin.datastructurealgorithm.problems.dynamicGraphConnectivity
import com.nalin.datastructurealgorithm.problems.kthSmallestInSubarray
import com.nalin.datastructurealgorithm.problems.maxOnPathQueries
import com.nalin.datastructurealgorithm.problems.minCostMaxFlow
import com.nalin.datastructurealgorithm.problems.nearestRedNodeQueries
import com.nalin.datastructurealgorithm.problems.shortestPathWithOneDiscount
import com.nalin.datastructurealgorithm.problems.twoSat
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@Ignore("Practice challenge stubs. Remove Ignore and solve one by one.")
class HardChallengesTest {

    @Test
    fun testDynamicGraphConnectivity() {
        val operations = listOf(
            DynamicConnectivityOperation(1, 1, 2),
            DynamicConnectivityOperation(1, 2, 3),
            DynamicConnectivityOperation(3, 1, 3),
            DynamicConnectivityOperation(2, 2, 3),
            DynamicConnectivityOperation(3, 1, 3),
            DynamicConnectivityOperation(1, 3, 4),
            DynamicConnectivityOperation(1, 2, 4),
            DynamicConnectivityOperation(3, 1, 4),
        )

        assertContentEquals(
            listOf(true, false, true),
            dynamicGraphConnectivity(4, operations)
        )
    }

    @Test
    fun testKthSmallestInSubarray() {
        val values = listOf(5, 1, 4, 2, 3)
        val queries = listOf(
            Triple(0, 4, 1),
            Triple(0, 4, 3),
            Triple(1, 3, 2),
        )

        assertContentEquals(
            listOf(1, 3, 2),
            kthSmallestInSubarray(values, queries)
        )
    }

    @Test
    fun testMinCostMaxFlow() {
        val edges = listOf(
            FlowEdge(0, 1, 2, 1),
            FlowEdge(0, 2, 1, 5),
            FlowEdge(1, 2, 1, 1),
            FlowEdge(1, 3, 1, 3),
            FlowEdge(2, 3, 2, 1),
        )

        assertEquals(3L to 14L, minCostMaxFlow(4, edges, 0, 3))
    }

    @Test
    fun testShortestPathWithOneDiscount() {
        val edges = listOf(
            WeightedEdge(1, 2, 10),
            WeightedEdge(2, 4, 10),
            WeightedEdge(1, 3, 1),
            WeightedEdge(3, 4, 100),
        )

        assertEquals(15L, shortestPathWithOneDiscount(4, edges, 1, 4))
    }

    @Test
    fun testCountDistinctSubstrings() {
        assertEquals(9L, countDistinctSubstrings("ababa"))
        assertEquals(6L, countDistinctSubstrings("aaaa"))
    }

    @Test
    fun testNearestRedNodeQueries() {
        val edges = listOf(
            TreeEdge(1, 2),
            TreeEdge(1, 3),
            TreeEdge(2, 4),
            TreeEdge(2, 5),
        )
        val operations = listOf(
            "query 5",
            "paint 5",
            "query 4",
            "query 3",
        )

        assertContentEquals(
            listOf(2, 2, 1),
            nearestRedNodeQueries(5, edges, operations.map {
                val parts = it.split(" ")
                parts[0] to parts[1].toInt()
            })
        )
    }

    @Test
    fun testMaxOnPathQueries() {
        val edges = listOf(
            TreeEdge(1, 2),
            TreeEdge(1, 3),
            TreeEdge(2, 4),
            TreeEdge(2, 5),
        )
        val initialValues = listOf(0, 5, 3, 7, 2, 6)
        val operations = listOf(
            "query 4 3",
            "update 3 10",
            "query 4 3",
            "query 5 4",
        )

        assertContentEquals(
            listOf(7, 10, 7),
            maxOnPathQueries(5, edges, initialValues, operations)
        )
    }

    @Test
    fun testTwoSat() {
        val satisfiable = twoSat(
            3,
            listOf(
                1 to 2,
                -1 to 3,
                -2 to -3,
            )
        )
        assertNotNull(satisfiable)

        val impossible = twoSat(
            1,
            listOf(
                1 to 1,
                -1 to -1,
            )
        )
        assertNull(impossible)
    }

    @Test
    fun testAlienDictionaryOrder() {
        assertEquals("wertf", alienDictionaryOrder(listOf("wrt", "wrf", "er", "ett", "rftt")))
        assertNull(alienDictionaryOrder(listOf("abc", "ab")))
    }
}
