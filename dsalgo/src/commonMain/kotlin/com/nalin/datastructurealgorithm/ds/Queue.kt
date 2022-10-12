package com.nalin.datastructurealgorithm.ds

class Queue<T> {
    var headNode: LinkedListNode<T>? = null
    var lastNode: LinkedListNode<T>? = null
    var size = 0;

    fun enqueue(value: T) {
        if (lastNode == null) {
            headNode = LinkedListNode(value)
            lastNode = headNode
        } else {
            lastNode?.nextNode = LinkedListNode(value)
            lastNode = lastNode?.nextNode
        }
        size++
    }

    fun dequeue(): T? {
        val node = headNode
        if (headNode?.nextNode == null) {
            headNode = null
            lastNode = null
        } else {
            headNode = headNode?.nextNode
        }
        size--
        return node?.value
    }

    fun peek(): T? {
        return headNode?.value
    }
}

class SetQueue<T> {
    val set = mutableSetOf<T>()

    fun enqueue(value: T) {
        set.add(value)
    }

    fun push(value: T) {
        set.add(value)
    }

    fun pop(): T? {
        val last = peekLast()
        set.remove(last)
        return last
    }

    fun dequeue(): T? {
        val first = peek()
        set.remove(first)
        return first
    }

    fun peek(): T? {
        return set.firstOrNull()
    }

    fun peekLast(): T? {
        return set.lastOrNull()
    }

    fun contains(value: T): Boolean {
        return set.contains(value)
    }

    fun size(): Int {
        return set.size
    }
}