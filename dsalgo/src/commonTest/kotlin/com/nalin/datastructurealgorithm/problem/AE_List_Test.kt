package com.nalin.datastructurealgorithm.problem

import com.nalin.datastructurealgorithm.ds.Queue
import com.nalin.datastructurealgorithm.ds.linkedListOf
import com.nalin.datastructurealgorithm.problems.*
import kotlin.test.Test
import kotlin.test.assertEquals

class AE_List_Test {

    @Test
    fun testCheckIntervalOveralps() {
        assertEquals(checkIntervalOveralps(listOf(1, 3), listOf(2, 4)), true)
        assertEquals(checkIntervalOveralps(listOf(1, 2), listOf(2, 4)), true)
        assertEquals(checkIntervalOveralps(listOf(1, 3), listOf(4, 5)), false)
        assertEquals(checkIntervalOveralps(listOf(4, 5), listOf(1, 3)), false)
        assertEquals(checkIntervalOveralps(listOf(2, 4), listOf(1, 2)), true)
    }

    @Test
    fun testMergeOverlappingIntervals() {
        assertEquals(
            mergeOverlappingIntervals(listOf(listOf(1, 3), listOf(2, 4), listOf(5, 6))),
            listOf(listOf(1, 4), listOf(5, 6))
        )
        assertEquals(
            mergeOverlappingIntervals(listOf()),
            listOf()
        )
    }

    @Test
    fun testFirstDuplicateValue() {
        assertEquals(firstDuplicateValue(mutableListOf(2, 1, 5, 2, 3, 3, 4)), 2)
    }

    @Test
    fun testLinkedListPalindrome() {
        assertEquals(linkedListPalindrome(linkedListOf(2)), true)
        assertEquals(linkedListPalindrome(linkedListOf(2, 3, 2)), true)
        assertEquals(linkedListPalindrome(linkedListOf(2, 2)), true)
        assertEquals(linkedListPalindrome(linkedListOf(2, 4)), false)
        assertEquals(linkedListPalindrome(linkedListOf(2, 3, 4)), false)
    }

    @Test
    fun testQueue() {
        val queue = Queue<Char>()
        queue.enqueue('a')
        queue.enqueue('b')
        queue.enqueue('c')
        assertEquals(queue.dequeue(), 'a')
        assertEquals(queue.dequeue(), 'b')
        assertEquals(queue.dequeue(), 'c')
        assertEquals(queue.dequeue(), null)
    }

    @Test
    fun testTandemCycle() {
        assertEquals(
            tandemBicycle(
                mutableListOf(5, 5, 3, 9, 2),
                mutableListOf(3, 6, 7, 2, 1),
                true
            ), 32
        );
        assertEquals(
            tandemBicycle(
                mutableListOf(5, 5, 3, 9, 2),
                mutableListOf(3, 6, 7, 2, 1),
                false
            ), 25
        );
        assertEquals(
            tandemBicycle(
                mutableListOf(),
                mutableListOf(),
                true
            ), 0
        );
    }

    @Test
    fun testClassPhotos() {
        assertEquals(classPhotos(mutableListOf(6, 9, 2, 4, 5), mutableListOf<Int>(5, 8, 1, 3, 4)), true);
        assertEquals(classPhotos(mutableListOf(5, 8, 1, 3, 4, 9), mutableListOf<Int>(6, 9, 2, 4, 5, 1)), false);
    }

    @Test
    fun testTwoNumberSum() {
        assertEquals(twoNumberSum(mutableListOf(9,11,1), 10), listOf(1,9));
    }

    @Test
    fun testRiverSizes() {
        assertEquals(riverSizes(listOf(listOf(1,1,1), listOf(1,0,0), listOf(1,0,1))), listOf(5,1));
        assertEquals(riverSizes(listOf(listOf(0,0,0), listOf(0,0,0))), listOf());
        assertEquals(riverSizes(listOf(listOf(0,0,0), listOf(0,1))), listOf(1));
        assertEquals(riverSizes(listOf(listOf())), listOf());
    }

    @Test
    fun testSubarraySort() {
//        assertEquals(subarraySort(listOf(1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19)), listOf(3,9))
//        assertEquals(subarraySort(listOf(1)), listOf(-1,-1))
//        assertEquals(subarraySort(listOf()), listOf(-1,-1))
//        assertEquals(subarraySort(listOf(1,2)), listOf(-1,-1))
//        assertEquals(subarraySort(listOf(1,2,3,4,3)), listOf(2,4))
        assertEquals(unsortedSubarray(listOf(1, 2, 4, 7, 10, 11, 7, 12, 13, 14, 16, 18, 19)), listOf(4,6))
    }

}