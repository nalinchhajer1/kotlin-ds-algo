package com.nalin.datastructurealgorithm.problem

import com.nalin.datastructurealgorithm.ds.toArray
import com.nalin.datastructurealgorithm.problems.*
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

    @Test
    fun testMergeSortedArray() {
        assertEquals(
            mergeSortedArray(
                arrayOf(null, null, null),
                arrayOf(1, 2, 3)
            ).joinToString { it.toString() }, arrayOf<Int?>(1, 2, 3).joinToString { it.toString() })

        assertEquals(
            mergeSortedArray(
                arrayOf(1, 2, 3, null, null, null),
                arrayOf(1, 2, 3)
            ).joinToString { it.toString() },
            arrayOf<Int?>(1, 1, 2, 2, 3, 3).joinToString { it.toString() })

        assertEquals(
            mergeSortedArray(
                arrayOf(1, 2, 3),
                arrayOf()
            ).joinToString { it.toString() },
            arrayOf<Int?>(1, 2, 3).joinToString { it.toString() })

        assertEquals(
            mergeSortedArray(
                arrayOf(10, 11, 12, null, null, null),
                arrayOf(1, 2, 3)
            ).joinToString { it.toString() },
            arrayOf<Int?>(1, 2, 3, 10, 11, 12).joinToString { it.toString() })

        assertEquals(
            mergeSortedArray(
                arrayOf(1, 2, 3, null, null, null),
                arrayOf(10, 11, 12)
            ).joinToString { it.toString() },
            arrayOf<Int?>(1, 2, 3, 10, 11, 12).joinToString { it.toString() })
    }

    @Test
    fun testMergeDuplicateChar() {
        assertEquals(mergeDuplicateChar("abccbc"), "ac")
        assertEquals(mergeDuplicateChar(""), "")
        assertEquals(mergeDuplicateChar("ab"), "ab")
        assertEquals(mergeDuplicateChar("abccba"), "")
    }

    @Test
    fun testFindPalindomeWithErrorProbabaility1() {
        assertEquals(findPalindomeWithErrorProbabaility1("tacocats"), true)
        assertEquals(findPalindomeWithErrorProbabaility1("racercar"), true)
        assertEquals(findPalindomeWithErrorProbabaility1("kbayak"), true)
        assertEquals(findPalindomeWithErrorProbabaility1("acbccba"), true)
        assertEquals(findPalindomeWithErrorProbabaility1("abcd"), false)
        assertEquals(findPalindomeWithErrorProbabaility1("btnnure"), false)
    }

    @Test
    fun testsumOfConsecutiveElement() {
        assertEquals(sumOfConsecutiveElement(listOf(1, 3, 1, 4, 23), 8), true)
        assertEquals(sumOfConsecutiveElement(listOf(1, 3, 1, 4, 23), 7), false)
//        assertEquals(sumOfConsecutiveElement(listOf(1, 3, 1, 4, 6), 7), false)
    }
}