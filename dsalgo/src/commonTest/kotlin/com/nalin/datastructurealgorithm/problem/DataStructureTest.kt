package com.nalin.datastructurealgorithm.problem

import com.nalin.datastructurealgorithm.ds.ArrayTreeNode
import com.nalin.datastructurealgorithm.ds.TreeNode
import com.nalin.datastructurealgorithm.problems.findFibonacciSeries
import com.nalin.datastructurealgorithm.problems.primeNumbers
import kotlin.test.Test
import kotlin.test.assertEquals

class DataStructureTest {

    @Test
    fun testFibSeries() {
        assertEquals(findFibonacciSeries(10), listOf<Int>(0, 1, 1, 2, 3, 5, 8, 13, 21, 34))
    }

    @Test
    fun testPrimeNumber() {
        assertEquals(primeNumbers(10000).size, 1229)
    }

    @Test
    fun testTree() {
        assertEquals(ArrayTreeNode.leftChildIndex(0),1)
        assertEquals(ArrayTreeNode.leftChildIndex(1),3)
        assertEquals(ArrayTreeNode.leftChildIndex(3),7)
        assertEquals(ArrayTreeNode.leftChildIndex(2),5)

        val node = ArrayTreeNode.createTree(arrayOf<Int?>(1,2,3,4,null,6))
        assertEquals(node?.value, 1)
        assertEquals(node?.leftNode?.value(), 2)
        assertEquals(node?.rightNode?.value(), 3)
        assertEquals(node?.leftNode?.leftNode()?.value(), 4)
        assertEquals(node?.leftNode?.rightNode(), null)
        assertEquals(node?.rightNode?.leftNode()?.value(), 6)
    }

}