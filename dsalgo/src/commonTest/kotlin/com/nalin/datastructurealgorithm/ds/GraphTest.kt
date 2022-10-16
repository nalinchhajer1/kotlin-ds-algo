package com.nalin.datastructurealgorithm.ds

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class GraphTest {

    var testSize = 100

    @Test
    fun testBasicGraph() {
        val dGraph = AdjacencyListGraph<Char>()
        dGraph.addEdge('1', '2', 3)
        dGraph.addEdge('1', '2', 3)
        dGraph.addNode('3')
        dGraph.addEdge('1', '2', 4)
        assertEquals(dGraph.edgesCount, 2)
        assertEquals(dGraph.removeEdge('1', '2'), true)
        assertEquals(dGraph.edgesCount, 0)
        assertEquals(dGraph.nodes['1'], mutableSetOf())
        assertEquals(dGraph.nodes['2'], mutableSetOf())

        dGraph.addEdge('1', '1', 5)
        assertEquals(dGraph.nodes['1']!!.size, 1)


        assertEquals(dGraph.containsNode('3'), true)
        dGraph.addEdge('1', '3', 4)
        dGraph.removeNode('3')

        assertEquals(dGraph.edgesCount, 1)

        val udGraph = AdjacencyListGraph<Char>(GRAPH_UNDIRECTED)
        udGraph.addEdge('1', '2', 3)
        udGraph.addEdge('1', '2', 3)
        udGraph.addNode('3')
        udGraph.addEdge('1', '2', 4)
        udGraph.addEdge('1', '1', 5)
        assertEquals(
            udGraph.nodes['1'],
            mutableSetOf(
                AdjacencyListGraph.Edge('2', 3),
                AdjacencyListGraph.Edge('2', 4),
                AdjacencyListGraph.Edge('1', 5)
            )
        )
        assertEquals(
            udGraph.nodes['2'],
            mutableSetOf(
                AdjacencyListGraph.Edge('1', 3),
                AdjacencyListGraph.Edge('1', 4),
            )
        )

        udGraph.removeNode('2')
        assertEquals(
            udGraph.nodes['1'],
            mutableSetOf(
                AdjacencyListGraph.Edge('1', 5)
            )
        )
        assertEquals(udGraph.edgesCount, 1)
        // change value
    }

    @Test
    fun adjacencyListGraphTest() {
        val graph = AdjacencyListGraph<Char>()
        for (i in 0 until testSize) {
            graph.addEdge('a' + i, 'a' + i + 1)
        }
        graph.addEdge('a', 'b')
        assertEquals(graph.nodeTraversalDFS().size, testSize + 1)

        val graph1 = AdjacencyListGraph<Char>(GRAPH_UNDIRECTED)
        for (i in 0 until testSize) {
            graph1.addEdge('a' + i, 'a' + i + 1)
        }
        assertEquals(graph1.nodeTraversalDFS().size, testSize + 1)

        val graph2 = AdjacencyListGraph<Char>(GRAPH_UNDIRECTED)
        graph2.addEdge('5', '2')
        graph2.addEdge('5', '0')
        graph2.addEdge('4', '0')
        graph2.addEdge('4', '1')
        graph2.addEdge('2', '3')
        graph2.addEdge('3', '1')
        graph2.addNode('7');
        assertEquals(graph2.nodeTraversalDFS(), listOf('5', '2', '3', '1', '4', '0', '7'))
        assertEquals(graph2.nodeTraversalBFS(), listOf('5', '2', '0', '3', '4', '1', '7'))
        assertEquals(
            graph2.nodeTraversalTopological(),
            listOf<Char>('0', '4', '1', '3', '2', '5', '7')
        )

    }

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

        graph.clear()
        assertEquals(graph.findShortestPath_dijkstra(1), mutableMapOf())
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
        val result = listOf(9, 0, 13, 10, 2, 3, 6, 1, 7, 11, 4, 12, 5, 8)
        assertEquals(
            graph.topologicalOrdering_KahnAlgorithm(),
            result
        )

        assertEquals(graph.validateTopologicalOrdering(result), true)

        graph.clear()
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        graph.addEdge(3, 1)
        assertEquals(graph.calculateInDegreeOfNodes(), mutableMapOf(1 to 1, 2 to 1, 3 to 1))
        assertEquals(
            graph.topologicalOrdering_KahnAlgorithm(),
            listOf(1, 2, 3)
        )
        assertEquals(graph.validateTopologicalOrdering(listOf(1, 2, 3)), false)

        graph.clear()
        assertEquals(
            graph.topologicalOrdering_KahnAlgorithm(),
            listOf()
        )
    }

    @Test
    fun testMST() {
        assertEquals(MSTEdge.create(1, 2, 2), MSTEdge.create(1, 2, 2))
        assertEquals(MSTEdge.create(1, 2, 2), MSTEdge.create(2, 1, 2))
        assertNotEquals(MSTEdge.create(1, 2, 3), MSTEdge.create(2, 1, 2))
        assertEquals(MSTEdge.create(1, 2, 2).hashCode(), MSTEdge.create(2, 1, 2).hashCode())
        assertEquals(MSTEdge.create("1", "2", 2).lNode, "1")
        assertEquals(MSTEdge.create("1", "2", 2).rNode, "2")

        val graph = AdjacencyListGraph<Char>(GRAPH_UNDIRECTED)
        graph.addEdge('A', 'B', 5)
        graph.addEdge('B', 'D', 2)
        graph.addEdge('A', 'D', 9)
        graph.addEdge('A', 'E', 1)
        graph.addEdge('E', 'D', 2)
        graph.addEdge('H', 'D', 2)
        graph.addEdge('E', 'F', 1)
        graph.addEdge('F', 'D', 5)
        graph.addEdge('F', 'G', 7)
        graph.addEdge('G', 'D', 11)
        graph.addEdge('G', 'H', 1)
        graph.addEdge('G', 'I', 4)
        graph.addEdge('H', 'I', 6)
        graph.addEdge('I', 'C', 1)
        graph.addEdge('H', 'C', 4)
        graph.addEdge('B', 'C', 4)
        graph.addEdge('I', 'J', 0)
        graph.addEdge('C', 'J', 8)
        assertEquals(graph.nodeCount, 10)
        assertEquals(graph.edgesCount, 18)

        val sortedValue = graph.getAllEdgesSortedByValue()
        assertEquals(sortedValue[0].value, 0)
        assertEquals(sortedValue.last().value, 11)

        var mst = graph.minumSpanningTree_kruskal()
        assertEquals(mst.nodeCount, 10)
        assertEquals(mst.edgesCount, 9)

        assertEquals(
            mst.nodeTraversalDFS('A'),
            mutableListOf('A', 'E', 'F', 'D', 'B', 'C', 'I', 'J', 'H', 'G')
        )

        graph.clear()
        graph.addEdge('1', '2')
        graph.addEdge('3', '4')
        graph.addEdge('1', '4')
        graph.addEdge('2', '3')
        graph.addEdge('1', '1')
        assertEquals(graph.edgesCount, 5);
        mst = graph.minumSpanningTree_kruskal()
        assertEquals(mst.nodeTraversalDFS('1'), mutableListOf('1', '2', '3', '4'))
        assertEquals(mst.edgesCount, 3);
    }

    @Test
    fun testFindStronglyConnectedComponents_torjan() {
        val graph = AdjacencyListGraph<Int>(GRAPH_DIRECTED)
        graph.addEdge(1, 5)
        graph.addEdge(5, 1)
        graph.addEdge(1, 2)
        graph.addEdge(5, 6)
        graph.addEdge(2, 6)
        graph.addEdge(6, 7)
        graph.addEdge(6, 3)
        graph.addEdge(3, 2)
        graph.addEdge(3, 7)
        graph.addEdge(3, 4)
        graph.addEdge(4, 7)
        graph.addEdge(3, 7)
        graph.addEdge(7, 8)
        graph.addEdge(8, 4)
        assertEquals(graph.findStronglyConnectedComponents_tarjan(), 3)
    }

}