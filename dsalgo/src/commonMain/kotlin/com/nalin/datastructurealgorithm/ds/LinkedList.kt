package com.nalin.datastructurealgorithm.ds

class LinkedListNode<T>(var value: T) {
    var nextNode: LinkedListNode<T>? = null
}

/**
 * Converts to Array, handle circular linked list
 */
fun <T> LinkedListNode<T>.toArray(): List<T> {
    val mutableList = mutableListOf<T>()
    var traverseNode = this;
    mutableList.add(traverseNode.value);

    while (traverseNode.nextNode !== null && traverseNode.nextNode !== this) {
        traverseNode = traverseNode.nextNode!!
        mutableList.add(traverseNode.value);
    }
    return mutableList;
}

fun <T> linkedListOf(vararg input: T): LinkedListNode<T>? {
    var headNode: LinkedListNode<T>? = null
    var lastNode: LinkedListNode<T>? = null
    input.forEach { value ->
        if (lastNode == null) {
            headNode = LinkedListNode(value)
            lastNode = headNode
        } else {
            lastNode?.nextNode = LinkedListNode(value)
            lastNode = lastNode?.nextNode
        }
    }
    return headNode
}

