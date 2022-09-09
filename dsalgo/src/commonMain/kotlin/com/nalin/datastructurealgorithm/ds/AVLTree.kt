package com.nalin.datastructurealgorithm.ds

import kotlin.math.abs
import kotlin.math.max

/**
 * AVL Tree node
 */
class BSTNode<T>(
    var value: T,
    var leftNode: BSTNode<T>? = null,
    var rightNode: BSTNode<T>? = null
) : TreeNode<T> {
    var height: Int = 1
    var count: Int = 1 // To manage duplicates
    inline fun balancingFactor(): Int {
        return (this.leftNode?.height ?: 0) - (this.rightNode?.height ?: 0)
    }

    inline fun maxChildHeight(): Int {
        return max(this.leftNode?.height ?: 0, this.rightNode?.height ?: 0)
    }

    override fun value(): T? {
        return value
    }

    override fun leftNode(): TreeNode<T>? {
        return leftNode
    }

    override fun rightNode(): TreeNode<T>? {
        return rightNode
    }
}

interface BSTTree<T : Comparable<T>> {
    fun root(): BSTNode<T>?
    fun insert(value: T)
}

fun <T : Comparable<T>> BSTTree<T>.search(value: T): Boolean {
    return find(value) != null
}

fun <T : Comparable<T>> BSTTree<T>.find(value: T): BSTNode<T>? {
    var traverseNode: BSTNode<T>? = root()
    while (traverseNode != null) {
        if (value == traverseNode.value) {
            return traverseNode;
        }
        traverseNode = if (value < traverseNode.value) {
            traverseNode.leftNode
        } else {
            traverseNode.rightNode
        }
    }
    return null
}

/**
 * Creates a self balancing Binary tree
 * Left is smaller than parent and right is greater than or equal to parent
 */
class AVLTree<T : Comparable<T>> : BSTTree<T> {

    private var rootNode: BSTNode<T>? = null

    override fun root(): BSTNode<T>? {
        return rootNode
    }

    /**
     * Insert given value on BST
     */
    override fun insert(value: T) {
        this.rootNode = _insertInBST(this.rootNode, value);
    }

    private fun _insertInBST(node: BSTNode<T>?, value: T): BSTNode<T> {
        if (node == null) {
            return BSTNode(value)
        }

        if (node.value == value) {
            node.count++
            return node
        }

        if (node.value < value) {
            node.rightNode = _insertInBST(node.rightNode, value)
        } else {
            node.leftNode = _insertInBST(node.leftNode, value)
        }
        node.height = node.maxChildHeight() + 1

        // Balancing
        if (abs(node.balancingFactor()) > 1) {
            return this._rotate(node, value)
        }
        return node
    }

    private fun _rotate(node: BSTNode<T>, value: T): BSTNode<T> {
        val balance = node.balancingFactor()
        if (balance > 1 && node.leftNode != null) {
            if (node.leftNode!!.value > value) {
                // left left
                return _rightRotate(node)
            } else {
                // left right
                node.leftNode = _leftRotate(node.leftNode!!)
                return _rightRotate(node)
            }
        } else if (balance < 1 && node.rightNode != null) {
            if (node.rightNode!!.value < value) {
                // right right
                return _leftRotate(node)
            } else {
                // right left
                node.rightNode = this._rightRotate(node.rightNode!!)
                return _leftRotate(node)
            }
        }
        return node;
    }

    // [c] -> [b,x] -> [a,y]
    // [b] -> [a,c] -> [y,x]
    private fun _rightRotate(parent: BSTNode<T>): BSTNode<T> {
        val child = parent.leftNode!!
        parent.leftNode = child.rightNode
        child.rightNode = parent

        parent.height = parent.maxChildHeight() + 1
        child.height = child.maxChildHeight() + 1

        return child
    }

    private fun _leftRotate(parent: BSTNode<T>): BSTNode<T> {
        val child = parent.rightNode!!
        parent.rightNode = child.leftNode
        child.leftNode = parent

        parent.height = parent.maxChildHeight() + 1
        child.height = child.maxChildHeight() + 1

        return child // child becomes parent
    }


}
