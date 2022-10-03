package com.nalin.datastructurealgorithm.ds

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