package com.nalin.datastructurealgorithm.ds

interface TreeNode<out T> {
    fun value(): T?
    fun leftNode(): TreeNode<T>?
    fun rightNode(): TreeNode<T>?
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
 * return min of node, or null
 */
fun <T> TreeNode<T>.max(): T? {
    var traverseNode: TreeNode<T>? = this;
    while (traverseNode?.rightNode() != null) {
        traverseNode = traverseNode.rightNode()
    }
    return traverseNode?.value()
}


class ArrayTreeNode<T>(
    var value: T? = null,
    var leftNode: TreeNode<T>? = null,
    var rightNode: TreeNode<T>? = null
) : TreeNode<T> {


    companion object {
        fun leftChildIndex(index: Int): Int {
            return (index shl 1) or 1
        }

        fun <T> createTree(array: Array<T>): ArrayTreeNode<T>? {
            fun _createTreeNode(rootNode: TreeNode<T>?, index: Int): ArrayTreeNode<T>? {
                if (index < array.size && array[index] != null) {
                    val node = ArrayTreeNode(array[index])
                    node.leftNode = _createTreeNode(rootNode, leftChildIndex(index))
                    node.rightNode = _createTreeNode(rootNode, leftChildIndex(index) + 1)
                    return node
                }
                return null
            }
            return _createTreeNode(null, 0)
        }
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




