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
    var nodeCount = 0
    var edgesCount = 0

    data class Edge<T>(val node: T, val value: Int)

    /**
     * Add Edge from node1 to node2, if node not created, it created one
     */
    fun addEdge(node1: T, node2: T, value: Int = 1) {
        if (addNode(node1).add(Edge(node2, value))) {
            edgesCount++
        }
        if (directionType == GRAPH_UNDIRECTED) {
            addNode(node2).add(Edge(node1, value))
        } else {
            addNode(node2)
        }

    }

    /**
     * Add Node
     */
    fun addNode(node: T): MutableSet<Edge<T>> {
        if (nodes[node] == null) {
            nodes[node] = mutableSetOf()
            nodeCount++
        }
        return nodes[node]!!
    }

    /**
     * return edges
     */
    fun getEdges(node: T): MutableIterator<Edge<T>> {
        return nodes[node]?.iterator() ?: mutableSetOf<Edge<T>>().iterator()
    }

    /**
     * get nodes iterator
     */
    fun getNodes(): MutableIterator<T> {
        return nodes.keys.iterator()
    }

    /**
     * Clear all nodes and Edges, it is same as new
     */
    fun clear() {
        nodes.clear()
        edgesCount = 0
        nodeCount = 0
    }

    /**
     * returns if empty
     */
    fun isEmpty(): Boolean {
        return nodes.isEmpty()
    }

    /**
     * Checks if contains Node
     */
    fun containsNode(node: T): Boolean {
        return nodes[node] != null
    }

    fun removeEdge(fromNode: T, toNode: T): Boolean {
        var result = false
        val iterator = getEdges(fromNode)
        while (iterator.hasNext()) {
            if (iterator.next().node == toNode) {
                iterator.remove()
                edgesCount--
                result = result || true
            }
        }
        return result
    }

    fun removeNode(node: T): MutableSet<Edge<T>>? {
        val result = nodes.remove(node)
        nodeCount--
        for (edgeNode in getNodes()) {
            removeEdge(edgeNode, node)
        }
        return result
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
        for (nextEdge in getEdges(node)) {
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

    if (containsNode(sourceNode)) {
        queue.push(sourceNode, 0)
        distance[sourceNode] = 0
        traverse()
    }

    return distance
}

/**
 * Return Topological sorting by Kahn Algorithm, first add all 0 our degree and then others
 */
fun <T : Comparable<T>> AdjacencyListGraph<T>.topologicalOrdering_KahnAlgorithm(): MutableList<T> {
    val indegreeNodes: MutableMap<T, Int> = calculateInDegreeOfNodes()
    val minDegree = indegreeNodes.values.minOfOrNull { v -> v } ?: 0
    val queue = Queue<T>();
    val topologicalSort = mutableListOf<T>()
    val nodeToAddInQueue = findNodesWithKDegree(indegreeNodes, minDegree)
    for (node in nodeToAddInQueue) {
        indegreeNodes.remove(node)
        queue.enqueue(node)
    }
    while (queue.size > 0) {
        val baseNode = queue.dequeue()
        topologicalSort.add(baseNode!!)
        for (edge in getEdges(baseNode)) {
            val edgeNode = edge.node
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
    for (node in getNodes()) {
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
    for (node in getNodes()) {
        inDegree[node] = inDegree[node] ?: 0
        for (edge in getEdges(node)) {
            inDegree[edge.node] = (inDegree[edge.node] ?: 0) + 1
        }
    }
    return inDegree
}

/**
 * Validate if given order is topological order, source is before destination
 */
fun <T> AdjacencyListGraph<T>.validateTopologicalOrdering(nodeInOrder: List<T>): Boolean {
    val nodeToPos = mutableMapOf<T, Int>()
    nodeInOrder.forEachIndexed { pos, item ->
        nodeToPos[item] = pos
    }

    for (node in getNodes()) {
        for (edge in getEdges(node)) {
            if (nodeToPos[node]!! >= nodeToPos[edge.node]!!) {
                return false
            }
        }
    }
    return true
}

/**
 * Node traversal as DFS
 * O (E+N)
 */
fun <T> AdjacencyListGraph<T>.nodeTraversalDFS(startNode: T? = null): MutableList<T> {
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

/**
 * Node traversal as BFS
 * O (E+N)
 */
fun <T> AdjacencyListGraph<T>.nodeTraversalBFS(startNode: T? = null): MutableList<T> {
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

/**
 * Node traversal in Topological
 * O (E+N)
 */
fun <T> AdjacencyListGraph<T>.nodeTraversalTopological(startNode: T? = null): MutableList<T> {
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

/**
 * Ensure lNode < rNode
 */
data class MSTEdge<T : Comparable<T>> private constructor(
    private val node1: T,
    private val node2: T,
    val value: Int
) {
    val lNode: T get() = node1
    val rNode: T get() = node2

    companion object {
        fun <T : Comparable<T>> create(lNode: T, rNode: T, value: Int): MSTEdge<T> {
            return MSTEdge(
                if (lNode < rNode) lNode else rNode,
                if (lNode < rNode) rNode else lNode,
                value
            )
        }
    }


}

// Minimum spanning tree, for undirected_graph
fun <T : Comparable<T>> AdjacencyListGraph<T>.minumSpanningTree_kruskal(): AdjacencyListGraph<T> {
    val sortedEdges = getAllEdgesSortedByValue()
    val mstGraph = AdjacencyListGraph<T>(GRAPH_UNDIRECTED)
    val unionFind = UnionFind<T>()
    for (edge in sortedEdges) {
        if (!unionFind.isConnected(edge.lNode, edge.rNode)) {
            unionFind.union(edge.lNode, edge.rNode)
            mstGraph.addEdge(edge.lNode, edge.rNode, edge.value)
        }
    }
    return mstGraph
}

fun <T : Comparable<T>> AdjacencyListGraph<T>.getAllEdgesSortedByValue(): List<MSTEdge<T>> {
    val edges = HashSet<MSTEdge<T>>()
    for (node in getNodes()) {
        for (edge in getEdges(node)) {
            edges.add(MSTEdge.create(node, edge.node, edge.value))
        }
    }

    val sortedEdges = edges.sortedBy { it.value }
    return sortedEdges
}


// Find all strongly connected components
/**
 * In the graph, strongly connected components are part of the graph that form cycles. Inside the cycles, all node can be visited from every other node
 * 1. We will use DFS + Stack to find strongly connected components
 * 2. We are going to mark a node with low link value, once we have visited all path on the DFS
 *
 * O(V+E)
 */
fun <T> AdjacencyListGraph<T>.findStronglyConnectedComponents_tarjan(): Int {
    var id = 0 // low-link id
    var sccCount = 0 //  number of strong connected component found
    val nodeIds = mutableMapOf<T, Int>() // We will use this to track if visited or not
    val nodeLowLinkId = mutableMapOf<T, Int>()
    val nodeOnStack = mutableMapOf<T, Boolean>()
    val stack = ArrayDeque<T>() // addLast. removeLast, size

    fun traverse(node: T) {
        stack.addLast(node)
        nodeOnStack[node] = true
        val lowLinkId = id++
        nodeIds[node] = lowLinkId
        nodeLowLinkId[node] = lowLinkId
        val edges = nodes[node]!!
        // Visit all neighbour and min low-link on callback
        for (edge in edges) {
            if (nodeIds[edge.node] == null) {
                traverse(edge.node)
            }
            // reached all nodes
            if (nodeOnStack[edge.node] != null) {
                nodeLowLinkId[node] = min(nodeLowLinkId[node] ?: 0, nodeLowLinkId[edge.node] ?: 0)
            }
        }

        // After visiting all neighbours, if we are at the start of SCC, empty the stack until we are back again at start of SCC (loop)
        if (nodeIds[node] == nodeLowLinkId[node]) {
            while (stack.size > 0) {
                val stackNode = stack.removeLast()
                nodeOnStack[stackNode] = false
                nodeLowLinkId[stackNode] = nodeIds[node]!!
                if (stackNode == node) {
                    break
                }
            }
            sccCount++
        }
    }

    for ((k, _) in nodes) {
        if (nodeIds[k] == null) {
            traverse(k)
        }
    }

    println("nodeLowLinkId ${nodeLowLinkId}")
    println("nodeIds ${nodeIds}")
    return sccCount
}