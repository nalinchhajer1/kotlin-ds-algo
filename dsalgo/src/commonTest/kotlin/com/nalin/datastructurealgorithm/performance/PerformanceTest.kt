package com.nalin.datastructurealgorithm.performance

import com.nalin.datastructurealgorithm.ds.*
import com.nalin.datastructurealgorithm.problems.getNthFib
import kotlin.math.ceil
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Checks scalability of datastructure and algorithm
 */
class PerformanceTest {
    val TEST_SIZE = 1_000_000

    @Test
    fun array_sequence_break_check() {
        val array = arrayOfNulls<Int>(TEST_SIZE) // 0 MS
        for (i in 0 until TEST_SIZE) { // 4 ms
            array[i] = i
        }

        var sequence: Int? = null // 1 ms
        for (i in array.indices) {
            if (array[i] == i) {
                sequence = i
            } else {
                break
            }
        }
        assertEquals(sequence, TEST_SIZE - 1)
        assertTrue { array.size == TEST_SIZE }
    }

    @Test
    fun mutable_array_sequence_break_check() {
        val array = mutableListOf<Int>() // 0 MS
        for (i in 0 until TEST_SIZE) { // 4 ms
            array.add(i)
        }

        var sequence: Int? = null // 1 ms
        for (i in array.indices) {
            if (array[i] == i) {
                sequence = i
            } else {
                break
            }
        }
        assertEquals(sequence, TEST_SIZE - 1)
        assertTrue { array.size == TEST_SIZE }
    }

    @Test
    fun set_performance() {
        val set = mutableSetOf<Int>()
        for (i in 0 until TEST_SIZE) { // 12 ms
            set.add(i)
        }

        var sequence: Int? = null // 21 ms
        for (i in 0 until TEST_SIZE) {
            if (set.contains(i)) {
                sequence = i
            } else {
                break
            }
        }

        assertEquals(sequence, TEST_SIZE - 1)
        assertTrue { set.size == TEST_SIZE }
    }

    @Test
    fun map_performance() {
        val map = hashMapOf<Int, Int>()
        for (i in 0 until TEST_SIZE) { // 11 ms
            map.put(i, 1)
        }

        var sequence: Int? = null // 16 ms
        for (i in 0 until TEST_SIZE) {
            if (map.get(i) == 1) {
                sequence = i
            } else {
                break
            }
        }

        assertEquals(sequence, TEST_SIZE - 1)
        assertTrue { map.size == TEST_SIZE }
    }

    @Test
    fun deleteItemFromArray() {
        val array = mutableListOf<Int>() // 0 MS
        for (i in 0 until TEST_SIZE) { // 4 ms
            array.add(i)
        }
//        var newArray = array.slice(0 until array.size)
        for (i in array.indices) {
//            array.removeFirst() // 524
            array.removeLast() // 16 ms
//            array.removeAt(0) // 484 ms
//            array.drop(1) // 2400 ms
//            newArray = array.slice(1 until newArray.size)  // 2819 ms
        }
    }

    @Test
    fun setQueue() {
        val queue = SetQueue<Int>()
        for (i in 1..TEST_SIZE) {
            queue.enqueue(i)
        }
        assertEquals(queue.size(), TEST_SIZE)
        while (queue.peek() != null) {
            queue.dequeue()
        }
        assertEquals(queue.size(), 0)
    }


    @Test
    fun merging2Array() {
        fun <T> merge(array1: List<T>, array2: List<T>): List<T> {
            return array1 + array2
        }

        val output = merge(Array(TEST_SIZE) { 1 }.toList(), Array(TEST_SIZE) { 2 }.toList())
        assertEquals(output.size, 2 * TEST_SIZE)
        assertEquals(output.sorted().toMutableList()[0], 1);
    }

    @Test
    fun testFib() {
        assertEquals(getNthFib(1), 0.0)
        assertEquals(getNthFib(2), 1.0)
        assertEquals(getNthFib(3), 1.0)
        assertEquals(getNthFib(6), 5.0)

        assertEquals(getNthFib(TEST_SIZE), Double.POSITIVE_INFINITY)
    }

    @Test
    fun testSplitArray() {
        fun splitArray(array: List<Int>, count: Int): MutableList<List<Int>> {
            val output = mutableListOf<List<Int>>()
            var subArray = mutableListOf<Int>()
            for (i in array.indices step count) {
                val maxSize = if (i + count < array.size) i + count else array.size;
                for (j in i until maxSize) {
                    subArray.add(array[j])
                }
                output.add(subArray)
                subArray = mutableListOf<Int>()
            }
            return output
        }

        val array = mutableListOf<Int>() // 0 MS
        for (i in 0 until TEST_SIZE) { // 4 ms
            array.add(i)
        }
        val count = 10
        val output = splitArray(array, count);
        assertEquals(output[0].size, count)
        assertEquals(output.size, ceil(array.size.toFloat() / count).toInt())
    }

    @Test
    fun avlTreePerformance() {
        val tree = AVLTree<Int>()
        for (i in TEST_SIZE downTo 0) {
            tree.insert(i)
        }
        assertEquals(tree.root()?.max(), TEST_SIZE)
        assertEquals(tree.root()?.min(), 0)
        assertEquals(tree.size(), TEST_SIZE + 1);
        assertEquals(tree.height(), 20)
        assertEquals(tree.root()?.value(), 475713)
        assertEquals(tree.root()?.leftNode()?.max(), 475712)
        assertEquals(tree.root()?.leftNode()?.min(), 0)
        assertEquals(tree.root()?.rightNode()?.min(), 475714)
        assertEquals(tree.root()?.rightNode()?.max(), TEST_SIZE)
        assertEquals(tree.isBST(), true);
        assertEquals(tree.isBalanced(), true);
        assertEquals(tree.isBalancedBST(), true);
    }

    @Test
    fun sortPerformance() {

        val array = MutableList(TEST_SIZE) {
            TEST_SIZE - it
        } // 64ms
//        val array = arrayListOf<Int>()
//        for (i in TEST_SIZE downTo 0) {
//            array.add(i)
//        } // 64ms
        val sorted = array.sorted()
        assertEquals(sorted.last(), TEST_SIZE)
        assertEquals(sorted.first(), 1)
    }
}