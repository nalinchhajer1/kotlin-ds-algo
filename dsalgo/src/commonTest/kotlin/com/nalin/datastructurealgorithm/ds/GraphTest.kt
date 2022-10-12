package com.nalin.datastructurealgorithm.ds

import kotlin.test.Test
import kotlin.test.assertEquals

class GraphTest {

    @Test
    fun testFindShortestPath_dijkstra() {
        val graph = AdjacencyListGraph<Int>()
        graph.addEdge(0, 1, 4)
        graph.addEdge(0, 7, 8)
        graph.addEdge(1, 2, 8)
        graph.addEdge(1, 7, 11)
        graph.addEdge(7, 6, 1)
        graph.addEdge(7, 0, 2)
        graph.addEdge(7, 8, 7)
        graph.addEdge(2, 8, 2)
        graph.addEdge(8, 6, 6)
        graph.addEdge(2, 5, 4)
        graph.addEdge(2, 3, 7)
        graph.addEdge(6, 5, 2)
        graph.addEdge(3, 5, 14)
        graph.addEdge(3, 4, 9)
        graph.addEdge(5, 4, 10)
        graph.addNode(9)
        val result = graph.findShortestPath_dijkstra(0)
        assertEquals(result[3], 19)
        assertEquals(result[4], 21)
        assertEquals(result[8], 14)
        assertEquals(result[9], null)
        assertEquals(
            result,
            mutableMapOf(
                0 to 0,
                1 to 4,
                2 to 12,
                3 to 19,
                4 to 21,
                5 to 11,
                6 to 9,
                7 to 8,
                8 to 14,
            )
        )
        assertEquals(
            graph.findShortestPath_dijkstra(7), mutableMapOf(
                0 to 2,
                1 to 6,
                2 to 14,
                3 to 21,
                4 to 13,
                5 to 3,
                6 to 1,
                7 to 0,
                8 to 7,
            )
        )
    }

    @Test
    fun test_topologicalOrdering_KahnAlgorithm() {
        val graph = AdjacencyListGraph<Int>()
        graph.addEdge(9, 2)
        graph.addEdge(9, 10)
        graph.addEdge(2, 6)
        graph.addEdge(10, 6)
        graph.addEdge(0, 2)
        graph.addEdge(0, 6)
        graph.addEdge(0, 3)
        graph.addEdge(6, 7)
        graph.addEdge(6, 11)
        graph.addEdge(7, 12)
        graph.addEdge(11, 12)
        graph.addEdge(7, 4)
        graph.addEdge(3, 1)
        graph.addEdge(3, 4)
        graph.addEdge(4, 5)
        graph.addEdge(4, 8)
        graph.addEdge(12, 8)
        graph.addEdge(1, 4)
        graph.addNode(13)
        assertEquals(graph.calculateInDegreeOfNodes()[13], 0)
        assertEquals(
            graph.topologicalOrdering_KahnAlgorithm(),
            listOf<Int>(9, 0, 13, 10, 2, 3, 6, 1, 7, 11, 4, 12, 5, 8)
        )
//        expect(topologicalOrderingKahnAlgorithm(listGraph)).toEqual(['0','9','13','3','2','10','1','6','7','11','4','12','5','8']);
    }

}