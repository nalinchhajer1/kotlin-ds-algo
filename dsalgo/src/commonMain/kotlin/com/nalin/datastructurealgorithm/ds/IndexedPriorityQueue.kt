package com.nalin.datastructurealgorithm.ds

/**
 * Perform remove operation and change priority operation
 */
class IndexedPriorityQueue<T : Comparable<T>>(
    sortByMin: Boolean = true
) {
    class InternalNode<T>(val value: T, var priority: Int) : Comparable<InternalNode<T>> {
        override fun compareTo(other: InternalNode<T>): Int {
            return this.priority.compareTo(other.priority)
        }
    }

    val heap = Heap<InternalNode<T>>(sortByMin, null)
    val dict = HashMap<T, Int>()

    init {
        heap.onIndexModified = this::onIndexChange
    }


    fun push(value: T, priority: Int) {
        if (dict[value] != null) {
            changePriority(value, priority)
        } else {
            dict[value] = heap.array.size
            heap.push(InternalNode(value, priority))
        }
    }

    fun peek(): T? {
        return heap.peek()?.value
    }

    fun pop(): T? {
        val result = heap.pop()?.value
        if (result != null) {
            dict.remove(result)
        }
        return result
    }

    fun changePriority(value: T, priority: Int) {
        heap.array[dict[value]!!].priority = priority
        heap.invalidate(dict[value]!!)
    }

    /**
     * Return priority of a node, if node not found, return -1
     */
    fun getPriority(value: T) : Int? {
        return if (dict[value] != null) {
            heap.array[dict[value]!!].priority
        } else {
            return null
        }
    }

    fun remove(value: T): T {
        val index = dict[value]!!
        dict.remove(value)
        return heap.remove(index).value
    }

    private fun onIndexChange(node: InternalNode<T>, index: Int) {
        dict[node.value] = index
    }

    fun contains(value: T) : Boolean {
        return dict[value] != null
    }

}