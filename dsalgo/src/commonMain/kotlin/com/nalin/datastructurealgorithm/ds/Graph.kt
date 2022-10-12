package com.nalin.datastructurealgorithm.ds

import kotlin.math.min

val GRAPH_UNDIRECTED = 1
val GRAPH_DIRECTED = 0

/**
 * Graph:
 * Create graph in format of Adjacency list. It stores connection for each node internally.
 */
class AdjacencyListGraph<T>(val directionType: Int = GRAPH_DIRECTED) {
    val nodes: MutableMap<T, MutableSet<Edge<T>>> = mutableMapOf()

    data class Edge<T>(val node: T, val value: Int) {
        override fun hashCode(): Int {
            return node.hashCode()
        }

        override fun equals(other: Any?): Boolean {
            return (other is Edge<*>) && node == other.node
        }
    }

    fun addEdge(node1: T, node2: T, value: Int = 1) {
        addNode(node1).add(Edge(node2, value))
        if (directionType == GRAPH_UNDIRECTED) {
            addNode(node2).add(Edge(node1, value))
        } else {
            addNode(node2)
        }
    }

    fun addNode(node: T): MutableSet<Edge<T>> {
        if (nodes[node] == null) {
            nodes[node] = mutableSetOf()
        }
        return nodes[node]!!
    }

    fun getEdges(node: T): MutableIterator<Edge<T>> {
        return nodes[node]?.iterator() ?: mutableSetOf<Edge<T>>().iterator()
    }

    fun getNodes(): MutableIterator<T> {
        return nodes.keys.iterator()
    }

    fun nodeTraversalDFS(startNode: T? = null): MutableList<T> {
        val traversal = mutableListOf<T>()
        val isVisited = mutableMapOf<T, Boolean>()
        fun traverse(node: T) {
            traversal.add(node)
            isVisited[node] = true
            val edges = nodes[node]!!
            for (edge in edges) {
                if (isVisited[edge.node] != true) {
                    traverse(edge.node)
                }
            }
        }

        if (startNode != null) {
            traverse(startNode)
        }

        for ((k, _) in nodes) {
            if (isVisited[k] != true) {
                traverse(k)
            }
        }
        return traversal
    }

    fun nodeTraversalBFS(startNode: T? = null): MutableList<T> {
        val traversal = mutableListOf<T>()
        val isVisited = mutableMapOf<T, Boolean>()
        val queue = SetQueue<T>()
        var traverse: (() -> Unit)? = null
        fun runQueue() {
            while (queue.peek() != null) {
                traverse?.let { it() }
            }
        }
        traverse = fun() {
            val node = queue.dequeue() ?: return
            traversal.add(node)
            isVisited[node] = true
            val edges = nodes[node]!!
            for (edge in edges) {
                if (isVisited[edge.node] != true) {
                    queue.enqueue(edge.node)
                }
            }
            runQueue()
        }

        if (startNode != null) {
            queue.enqueue(startNode)
            runQueue()
        }

        for ((k, _) in nodes) {
            if (isVisited[k] != true) {
                queue.enqueue(k)
                runQueue()
            }
        }
        return traversal
    }

    fun nodeTraversalTopological(startNode: T? = null): MutableList<T> {
        val traversal = mutableListOf<T>()
        val isVisited = mutableMapOf<T, Boolean>()
        fun traverse(node: T) {
            isVisited[node] = true
            val edges = nodes[node]!!
            for (edge in edges) {
                if (isVisited[edge.node] != true) {
                    traverse(edge.node)
                }
            }
            traversal.add(node)
        }

        if (startNode != null) {
            traverse(startNode)
        }

        for ((k, _) in nodes) {
            if (isVisited[k] != true) {
                traverse(k)
            }
        }
        return traversal
    }

}

/**
 * Dijkstra Algorithm
 * Given a graph and a source vertex in the graph, find the shortest paths from the source to all vertices in the given graph.
 * O((E+V)log V)
 */
fun <T : Comparable<T>> AdjacencyListGraph<T>.findShortestPath_dijkstra(
    sourceNode: T
): MutableMap<T, Int> {
    // use priority queue to select shortest possible destination
    val distance = mutableMapOf<T, Int>() // Stores distance for each node
    val queue = IndexedPriorityQueue<T>(true)
    val visited = mutableMapOf<T, Boolean>()

    fun traverse() {
        val node = queue.pop()!!

        visited[node] = true
        val traversal = getEdges(node)
        while (traversal.hasNext()) {
            val nextEdge = traversal.next();
            if (visited[nextEdge.node] != true) {
                val newDistance =
                    min(distance[nextEdge.node] ?: Int.MAX_VALUE, distance[node]!! + nextEdge.value)
                distance[nextEdge.node] = newDistance
                if (queue.contains(nextEdge.node)) {
                    if (queue.getPriority(nextEdge.node)!! < newDistance) {
                        queue.changePriority(nextEdge.node, newDistance)
                    }
                } else {
                    queue.push(nextEdge.node, newDistance)
                }
            }
        }
        if (queue.peek() != null) {
            traverse()
        }

    }

    queue.push(sourceNode, 0)
    distance[sourceNode] = 0
    traverse()

    return distance
}

/**
 * Return Topological sorting by Kahn Algorithm, first add all 0 our degree and then others
 */
fun <T : Comparable<T>> AdjacencyListGraph<T>.topologicalOrdering_KahnAlgorithm(): MutableList<T> {
    val indegreeNodes: MutableMap<T, Int> = calculateInDegreeOfNodes()
    val queue = Queue<T>();
    val topologicalSort = mutableListOf<T>()
    val nodeToAddInQueue = findNodesWithKDegree(indegreeNodes, 0)
    for (node in nodeToAddInQueue) {
        indegreeNodes.remove(node)
        queue.enqueue(node)
    }
    while (queue.size > 0) {
        val baseNode = queue.dequeue()
        topologicalSort.add(baseNode!!)
        val traversal = getEdges(baseNode)
        while (traversal.hasNext()) {
            val edgeNode = traversal.next().node
            if (indegreeNodes[edgeNode] != null) {
                indegreeNodes[edgeNode] = indegreeNodes[edgeNode]!! - 1
                if (indegreeNodes[edgeNode] == 0) {
                    indegreeNodes.remove(edgeNode);
                    queue.enqueue(edgeNode)
                }
            }
        }
    }
    return topologicalSort
}

fun <T> AdjacencyListGraph<T>.findNodesWithKDegree(
    indegreeNode: MutableMap<T, Int>,
    degree: Int
): MutableList<T> {
    val output = mutableListOf<T>()
    val nodeIterator = getNodes()
    while (nodeIterator.hasNext()) {
        val node = nodeIterator.next()
        if ((indegreeNode[node] ?: 0) == degree) {
            output.add(node)
        }
    }
    return output
}

/**
 * Calculates Indegee of all nodes
 */
fun <T> AdjacencyListGraph<T>.calculateInDegreeOfNodes(): MutableMap<T, Int> {
    val inDegree = mutableMapOf<T, Int>()
    val nodeIterator = getNodes()
    while (nodeIterator.hasNext()) {
        val node = nodeIterator.next()
        inDegree[node] = inDegree[node] ?: 0
        val edgeIterator = getEdges(node)
        while (edgeIterator.hasNext()) {
            val edge = edgeIterator.next()
            inDegree[edge.node] = (inDegree[edge.node] ?: 0) + 1
        }
    }
    return inDegree
}