package com.nalin.datastructurealgorithm.problem

import com.nalin.datastructurealgorithm.ds.*
import kotlin.test.Test
import kotlin.test.assertEquals

class TreeTest {

    @Test
    fun testTree() {

        assertEquals(ArrayTreeNode.leftChildIndex(0), 1)
        assertEquals(ArrayTreeNode.leftChildIndex(1), 3)
        assertEquals(ArrayTreeNode.leftChildIndex(3), 7)
        assertEquals(ArrayTreeNode.leftChildIndex(2), 5)

        val tree = ArrayTree(arrayOf(1, 2, 3, 4, null, 6))
        val node = tree.root()
        assertEquals(node?.value(), 1)
        assertEquals(node?.leftNode?.value(), 2)
        assertEquals(node?.rightNode?.value(), 3)
        assertEquals(node?.leftNode?.leftNode?.value(), 4)
        assertEquals(node?.leftNode?.rightNode, null)
        assertEquals(node?.rightNode?.leftNode()?.value(), 6)
        assertEquals(tree.height(), 3);
        assertEquals(tree.isBST(), false);
        assertEquals(tree.isBalanced(), true);

        assertEquals(ArrayTree(arrayOf(4, 2, 7, 1, 3)).isBST(), true)
        assertEquals(ArrayTree(arrayOf(4, 6)).isBST(), false)
        assertEquals(ArrayTree(arrayOf(4, 2, 3)).isBST(), false)
        assertEquals(ArrayTree(arrayOf(4, 4, 7)).isBST(), true)
        assertEquals(ArrayTree(arrayOf(4, 2, null, 1, 3)).isBalanced(), false);
        assertEquals(ArrayTree(arrayOf(4)).isBalanced(), true);
        assertEquals(ArrayTree(arrayOf(4, 2)).isBalanced(), true);


        val root = ArrayTreeNode(10);
        root.leftNode = ArrayTreeNode(7)
        root.leftNode?.leftNode = ArrayTreeNode(6)
        root.leftNode?.leftNode?.leftNode = ArrayTreeNode(5)
        root.rightNode = ArrayTree(arrayOf(14, 12, 17, 11, 13)).root()
        assertEquals(root.isBST(), true)
        assertEquals(root.isBalanced(), false)
        assertEquals(root.rightNode?.isBalanced(), true)

        root.rightNode = ArrayTree(arrayOf(4, 2, 7, 1, 3)).root()
        assertEquals(root.rightNode?.isBST(), true)
        assertEquals(root.isBST(), false)

    }
}