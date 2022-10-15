package com.nalin.datastructurealgorithm.ds

import kotlin.math.abs
import kotlin.math.max

interface BTree<out T> {
    fun root(): BTreeNode<T>?
}

interface BTreeNode<out T> {
    fun value(): T
    fun leftNode(): BTreeNode<T>?
    fun rightNode(): BTreeNode<T>?
}

interface BSTTree<T : Comparable<T>> : BTree<T> {
    fun insert(value: T)
}

fun <T : Comparable<T>> BTree<T>.search(value: T): Boolean {
    return find(value) != null
}

fun <T : Comparable<T>> BTree<T>.find(value: T): BTreeNode<T>? {
    var traverseNode: BTreeNode<T>? = root()
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
fun <T : Comparable<T>> BTree<T>.isBST(): Boolean {
    return root()?.isBST() ?: true
}

fun <T : Comparable<T>> BTree<T>.height(): Int {
    return root()?.height() ?: 0
}

fun <T> BTree<T>.isBalanced(): Boolean {
    return root()?.isBalanced() ?: true
}

fun <T : Comparable<T>> BTree<T>.isBalancedBST(): Boolean {
    return isBST() && isBalanced()
}

/**
 * return array of distinct node value
 */
fun <T> BTreeNode<T>.treeTraversalLTR(): List<T> {
    val array = mutableListOf<T>()
    fun _traversal(node: BTreeNode<T>?) {
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
fun <T> BTreeNode<T>.min(): T? {
    var traverseNode: BTreeNode<T>? = this;
    while (traverseNode?.leftNode() != null) {
        traverseNode = traverseNode.leftNode()
    }
    return traverseNode?.value()
}

/**
 * return min of node, or null
 */
fun <T> BTreeNode<T>.max(): T? {
    var traverseNode: BTreeNode<T>? = this;
    while (traverseNode?.rightNode() != null) {
        traverseNode = traverseNode.rightNode()
    }
    return traverseNode?.value()
}

/**
 * Depth of tree
 */
fun <T> BTreeNode<T>.height(): Int {
    return max((leftNode()?.height() ?: 0), (rightNode()?.height() ?: 0)) + 1
}

/**
 * Check if inorder traversal is in sorted order
 */
fun <T : Comparable<T>> BTreeNode<T>.isBST(): Boolean {
    var lastVisit: T? = null
    fun _traversal(node: BTreeNode<T>?): Boolean {
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
fun <T> BTreeNode<T>.isBalanced(): Boolean {
    return checkBalance() >= 0
}

/**
 * use height logic to check balance, if balance failed, avoid computation and return -1, else return height
 */
private fun <T> BTreeNode<T>.checkBalance(): Int {
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


class ArrayTree<T>(array: Array<T?>) : BTree<T> {
    var root: ArrayTreeNode<T>? = null
    var size: Int = 0

    init {
        fun _createTreeNode(rootNode: BTreeNode<T>?, index: Int): ArrayTreeNode<T>? {
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

    fun size(): Int {
        return size
    }

}

class ArrayTreeNode<T>(
    value: T,
    var leftNode: ArrayTreeNode<T>? = null,
    var rightNode: ArrayTreeNode<T>? = null
) : BTreeNode<T> by TreeNodeImpl(value) {


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

class TreeNodeImpl<T>(
    var value: T,
    var leftNode: TreeNodeImpl<T>? = null,
    var rightNode: TreeNodeImpl<T>? = null
) : BTreeNode<T> {
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




