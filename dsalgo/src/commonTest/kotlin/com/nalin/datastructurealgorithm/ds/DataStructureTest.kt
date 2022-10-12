package com.nalin.datastructurealgorithm.ds

import kotlin.test.Test
import kotlin.test.assertEquals

class DataStructureTest {
    var testSize = 10000

    @Test
    fun testLinkedList() {
        val range = (1..testSize).toList().toTypedArray()
        val linkedList = linkedListOf<Int>(*range)
        var itratorNode = linkedList!!
        val result = arrayListOf<Int>()
        while (itratorNode.nextNode !== null) {
            result.add(itratorNode.value ?: 0)
            itratorNode = itratorNode.nextNode!!
        }
        result.add(itratorNode.value ?: 0)
        assertEquals(result, arrayListOf<Int>(*range))
    }

    @Test
    fun heapTest() {
        val heap = Heap<Int>(false)
        for (x in 0 until testSize) {
            heap.push(x)
        }
        assertEquals(heap.array.size, testSize)
        var isValid = true
        var expectedValue = testSize
        for (x in testSize - 1 downTo 0) {
            expectedValue--
            isValid = isValid && (expectedValue == heap.pop())
        }
        assertEquals(isValid, true)
        assertEquals(heap.array.size, 0)
    }

    @Test
    fun indexedPriorityQueueTest() {
        val ipq = IndexedPriorityQueue<Char>(false)
        for (x in 0 until testSize) {
            ipq.push('a' + x, x)
        }
        var isValid = true
        var expectedValue = 'a' + testSize
        for (x in testSize - 1 downTo 0) {
            expectedValue--
            isValid = isValid && (expectedValue == ipq.pop())
        }
        assertEquals(isValid, true)
    }

    @Test
    fun removeFromHeap() {
        val ipq = IndexedPriorityQueue<Char>(false)
        ipq.push('a', 3)
        ipq.push('b', 4)
        ipq.push('c', 5)
        ipq.push('d', 5)
        ipq.push('e', 100)
        ipq.remove('b')
        ipq.remove('c')
        ipq.remove('d')
        assertEquals(ipq.peek(), 'e')
        ipq.changePriority('e', 2)
        assertEquals(ipq.pop(), 'a')
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
        assertEquals(graph2.nodeTraversalDFS(), listOf<Char>('5', '2', '3', '1', '4', '0', '7'))
        assertEquals(graph2.nodeTraversalBFS(), listOf<Char>('5', '2', '0', '3', '4', '1', '7'))
        assertEquals(
            graph2.nodeTraversalTopological(),
            listOf<Char>('0', '4', '1', '3', '2', '5', '7')
        )

    }

    @Test
    fun setQueue() {
        val queue = SetQueue<Int>()
        queue.enqueue(1)
        queue.enqueue(3)
        queue.enqueue(4)
        queue.enqueue(-1)
        queue.enqueue(4)
        assertEquals(queue.contains(1), true)
        assertEquals(queue.dequeue(), 1)
        assertEquals(queue.contains(1), false)
        assertEquals(queue.dequeue(), 3)
        assertEquals(queue.dequeue(), 4)
        assertEquals(queue.dequeue(), -1)
        assertEquals(queue.dequeue(), null)

        // Using this as Stack
        val stack = SetQueue<Int>()
        stack.push(1)
        stack.push(3)
        stack.push(4)
        stack.push(-1)
        stack.push(4)
        assertEquals(stack.pop(), -1)
        assertEquals(stack.pop(), 4)
        assertEquals(stack.pop(), 3)
        assertEquals(stack.pop(), 1)
        assertEquals(stack.pop(), null)
    }
}