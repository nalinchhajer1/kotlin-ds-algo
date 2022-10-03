package com.nalin.datastructurealgorithm.problems

import com.nalin.datastructurealgorithm.ds.LinkedListNode

/**
 * Find minimum value from sorted array. Note that array is shifted by some amount.
 */
fun findMinimum_shiftedSortedArray(array: Array<Int>): Int {
    println(array.joinToString());
    var start = 0
    var end = array.size - 1
    var index = -1

    while (start <= end) {
        val mid = start + ((end - start) shr 1)
        println("mid-${mid}, start-${start}, end-${end}")
        if (array[mid] <= array[end]) {
            index = start
            end = mid - 1
        } else {
            index = mid
            start = mid + 1
        }
    }

    return index
}

/**
 * Insert a number in circlular linked list where elements are inserted in sorted order
 */
fun insertCircularLinkedList(
    rootNode: LinkedListNode<Int>?,
    value: Int,
    directionAsc: Boolean = true
): LinkedListNode<Int> {
    if (rootNode === null) {
        return createCircularListNode(value)
    }

    var traverseNode: LinkedListNode<Int> = rootNode;
    while (traverseNode.nextNode !== rootNode) {
        val foundNode = if (traverseNode.value <= traverseNode.nextNode!!.value) {
            traverseNode.value <= value && value <= traverseNode.nextNode!!.value
        } else {
            traverseNode.value <= value || value <= traverseNode.nextNode!!.value
        }

        if (foundNode) {
            insertAsCircularNode(traverseNode, value)
            return rootNode;
        } else {
            traverseNode = traverseNode.nextNode!!
        }
    }

    insertAsCircularNode(traverseNode, value)
    return rootNode;
}

private fun createCircularListNode(value: Int): LinkedListNode<Int> {
    val listNode = LinkedListNode(value)
    listNode.nextNode = listNode;
    return listNode
}

private fun insertAsCircularNode(node: LinkedListNode<Int>, value: Int) {
    val temp = node.nextNode
    val newNode = createCircularListNode(value);
    node.nextNode = newNode
    newNode.nextNode = temp
}

/**
 * Rotational Cipher : Rotate alphabet / number by k digit
 */
fun rotateCipher(text: String, digit: Int): String {
    var output = StringBuilder()
    for (ch in text) {
        if ('a' >= ch && ch <= 'z') {
            output.append(ch + digit)
        } else if ('A' >= ch && ch <= 'Z') {
            output.append(ch + digit)
        } else if ('0' >= ch && ch <= '9') {
            output.append(ch + digit)
        } else {
            output.append(ch + digit)
        }
    }
    return output.toString()
}
