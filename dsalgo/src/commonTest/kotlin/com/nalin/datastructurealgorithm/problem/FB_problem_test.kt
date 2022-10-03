package com.nalin.datastructurealgorithm.problem

import com.nalin.datastructurealgorithm.ds.toArray
import com.nalin.datastructurealgorithm.problems.findMinimum_shiftedSortedArray
import com.nalin.datastructurealgorithm.problems.insertCircularLinkedList
import kotlin.test.Test
import kotlin.test.assertEquals

class FB_problem_test {

    @Test
    fun check_findMinimum_shiftedSortedArray() {
        assertEquals(findMinimum_shiftedSortedArray(arrayOf<Int>()), -1)
        assertEquals(findMinimum_shiftedSortedArray(arrayOf<Int>(3)), 0)
        assertEquals(findMinimum_shiftedSortedArray(arrayOf<Int>(3, 5, 9, 10)), 0)
        assertEquals(findMinimum_shiftedSortedArray(arrayOf<Int>(10, 9, 3, 5)), 2)
        assertEquals(findMinimum_shiftedSortedArray(arrayOf<Int>(4, 1, 3)), 1)
        assertEquals(findMinimum_shiftedSortedArray(arrayOf<Int>(4, 5, 6, 7, 8, 10, 9, 3)), 7)
    }

    @Test
    fun check_insertCircularLinkedList() {
        val root = insertCircularLinkedList(null, 10)
        assertEquals(root.toArray(), listOf(10))
        insertCircularLinkedList(root, 14)
        assertEquals(root.toArray(), listOf(10, 14))
        insertCircularLinkedList(root, 6)
        assertEquals(root.toArray(), listOf(10, 14, 6))
        insertCircularLinkedList(root, 11)
        insertCircularLinkedList(root, 9)
        insertCircularLinkedList(root, 5)
        insertCircularLinkedList(root, 7)
        assertEquals(root.toArray(), listOf(10, 11, 14, 5, 6, 7, 9))
        insertCircularLinkedList(root, 14)
        insertCircularLinkedList(root, 13)
        insertCircularLinkedList(root, 12)
        insertCircularLinkedList(root, 11)
        insertCircularLinkedList(root, 10)
        insertCircularLinkedList(root, 9)
        insertCircularLinkedList(root, 8)
        insertCircularLinkedList(root, 7)
        insertCircularLinkedList(root, 6)
        insertCircularLinkedList(root, 5)
        assertEquals(
            root.toArray(),
            listOf(10, 10, 11, 11, 12, 13, 14, 14, 5, 5, 6, 6, 7, 7, 8, 9, 9)
        )
    }
}