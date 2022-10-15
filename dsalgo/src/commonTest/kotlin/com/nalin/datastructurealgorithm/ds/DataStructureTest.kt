package com.nalin.datastructurealgorithm.ds

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

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

    @Test
    fun testUnionFind() {
        val set = UnionFind<Int>()
        set.union(0, 1)
        set.union(0, 2)
        set.union(1, 3)
        assertEquals(set.find(0), set.find(3))
        assertNotEquals(set.find(0), set.find(4))
        assertEquals(set.parents, mutableMapOf(0 to 0, 1 to 0, 2 to 0, 3 to 0, 4 to 4))
        set.union(8, 9)
        set.union(5, 6)
        set.union(4, 7)
        assertEquals(set.find(4), set.find(7))
        assertEquals(set.find(7), set.find(4))
        set.union(6, 7)
        set.union(9, 7)
        assertEquals(
            set.parents,
            mutableMapOf(
                0 to 0,
                1 to 0,
                2 to 0,
                3 to 0,
                4 to 5,
                8 to 8,
                9 to 8,
                5 to 8,
                6 to 5,
                7 to 5
            )
        )
        assertEquals(set.isConnected(9,0), false)
        assertEquals(set.isConnected(9,3), false)
        assertEquals(set.isConnected(6,9), true)
        assertEquals(set.isConnected(6,6), true)
    }
}