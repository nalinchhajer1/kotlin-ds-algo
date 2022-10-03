package com.nalin.datastructurealgorithm.ds

import kotlin.math.abs
import kotlin.math.max

interface TreeNode<out T> {
    fun value(): T
    fun leftNode(): TreeNode<T>?
    fun rightNode(): TreeNode<T>?
}

interface Tree<out T> {
    fun root(): TreeNode<T>?
    fun size(): Int
}

class TreeNodeImpl<T>(
    var value: T,
    var leftNode: TreeNodeImpl<T>? = null,
    var rightNode: TreeNodeImpl<T>? = null
) : TreeNode<T> {
    override fun value(): T {
        return value
    }

    override fun leftNode(): TreeNodeImpl<T>? {
        return leftNode
    }

    override fun rightNode(): TreeNodeImpl<T>? {
        return rightNode
    }
}

fun <T : Comparable<T>> Tree<T>.search(value: T): Boolean {
    return find(value) != null
}

fun <T : Comparable<T>> Tree<T>.find(value: T): TreeNode<T>? {
    var traverseNode: TreeNode<T>? = root()
    while (traverseNode != null) {
        if (value == traverseNode.value()) {
            return traverseNode;
        }
        traverseNode = if (value < traverseNode.value()) {
            traverseNode.leftNode()
        } else {
            traverseNode.rightNode()
        }
    }
    return null
}

/**
 * Check if given tree hold BST property and it is balanced
 */
fun <T : Comparable<T>> Tree<T>.isBST(): Boolean {
    return root()?.isBST() ?: true
}

fun <T : Comparable<T>> Tree<T>.height(): Int {
    return root()?.height() ?: 0
}

fun <T> Tree<T>.isBalanced(): Boolean {
    return root()?.isBalanced() ?: true
}

fun <T : Comparable<T>> Tree<T>.isBalancedBST(): Boolean {
    return isBST() && isBalanced()
}

/**
 * return array of distinct node value
 */
fun <T> TreeNode<T>.treeTraversalLTR(): List<T> {
    val array = mutableListOf<T>()
    fun _traversal(node: TreeNode<T>?) {
        if (node?.value() == null) return
        _traversal(node.leftNode())
        array.add(node.value()!!)
        _traversal(node.rightNode())
    }
    _traversal(this)
    return array
}

/**
 * return min of node, or null
 */
fun <T> TreeNode<T>.min(): T? {
    var traverseNode: TreeNode<T>? = this;
    while (traverseNode?.leftNode() != null) {
        traverseNode = traverseNode.leftNode()
    }
    return traverseNode?.value()
}

/**
 * Depth of tree
 */
fun <T> TreeNode<T>.height(): Int {
    return max((leftNode()?.height() ?: 0), (rightNode()?.height() ?: 0)) + 1
}

/**
 * Check if inorder traversal is in sorted order
 */
fun <T : Comparable<T>> TreeNode<T>.isBST(): Boolean {
    var lastVisit: T? = null
    fun _traversal(node: TreeNode<T>?): Boolean {
        if (node == null) return true
        if (!_traversal(node.leftNode())) {
            return false
        }
        if (lastVisit != null && lastVisit!! > node.value()) {
            return false
        }
        lastVisit = node.value()
        return _traversal(node.rightNode())
    }

    return _traversal(this)
}

/**
 * Check if given tree is balanced
 */
fun <T> TreeNode<T>.isBalanced(): Boolean {
    return checkBalance() >= 0
}

/**
 * use height logic to check balance, if balance failed, avoid computation and return -1, else return height
 */
private fun <T> TreeNode<T>.checkBalance(): Int {
    val lh: Int = leftNode()?.checkBalance() ?: 0
    if (lh == -1) {
        return -1
    }
    val rh: Int = rightNode()?.checkBalance() ?: 0
    if (rh == -1) {
        return -1
    }

    if (abs(lh - rh) > 1) {
        return -1;
    }
    return max(lh, rh) + 1
}

/**
 * return min of node, or null
 */
fun <T> TreeNode<T>.max(): T? {
    var traverseNode: TreeNode<T>? = this;
    while (traverseNode?.rightNode() != null) {
        traverseNode = traverseNode.rightNode()
    }
    return traverseNode?.value()
}


class ArrayTree<T>(array: Array<T?>) : Tree<T> {
    var root: ArrayTreeNode<T>? = null
    var size: Int = 0

    init {
        fun _createTreeNode(rootNode: TreeNode<T>?, index: Int): ArrayTreeNode<T>? {
            if (index < array.size && array[index] != null) {
                val node = ArrayTreeNode<T>(array[index]!!)
                size++
                node.leftNode = _createTreeNode(rootNode, ArrayTreeNode.leftChildIndex(index))
                node.rightNode = _createTreeNode(rootNode, ArrayTreeNode.leftChildIndex(index) + 1)
                return node
            }
            return null
        }
        root = _createTreeNode(null, 0)
    }

    override fun root(): ArrayTreeNode<T>? {
        return root
    }

    override fun size(): Int {
        return size
    }

}

class ArrayTreeNode<T>(
    value: T,
    var leftNode: ArrayTreeNode<T>? = null,
    var rightNode: ArrayTreeNode<T>? = null
) : TreeNode<T> by TreeNodeImpl(value) {


    companion object {
        fun leftChildIndex(index: Int): Int {
            return (index shl 1) or 1
        }
    }

    override fun leftNode(): ArrayTreeNode<T>? {
        return leftNode
    }

    override fun rightNode(): ArrayTreeNode<T>? {
        return rightNode
    }

}




