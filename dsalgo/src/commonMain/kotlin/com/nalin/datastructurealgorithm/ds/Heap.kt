package com.nalin.datastructurealgorithm.ds

/**
 * Min heap, max heap, priority queue
 * sortByMin = false is max heap else min heap
 */

class Heap<T : Comparable<T>>(
    val sortByMin: Boolean = true,
    val initialArray: List<T>? = null
) {
    val array: MutableList<T> = mutableListOf()
    var onIndexModified: ((value: T, index: Int) -> Unit)? = null

    init {
        if (initialArray != null) {
            for (item in initialArray) {
                push(item)
            }
        }
    }


    /**
     * Push a value
     */
    fun push(value: T) {
        val lastIndex = array.size
        array.add(value)
        onIndexModified?.let { it(value, lastIndex) }
        moveUp(lastIndex)
    }

    /**
     * Pop min/max value
     */
    fun pop(): T? {
        if (array.size > 1) {
            val firstElement = array[0]
            val last = array.removeLast()
            array[0] = last
            onIndexModified?.let { it(last, 0) }
            moveDown(0)
            return firstElement
        } else if (array.size == 1) {
            return array.removeLast()
        }
        return null
    }

    /**
     * check the value which will be popped
     */
    fun peek(): T? {
        if (array.size > 0) {
            return array[0]
        }
        return null
    }

    /**
     * If a value is change, call invalidate. It will fix heap
     */
    fun invalidate(index: Int) {
        val parentIndex = parentIndex(index)
        if (parentIndex > 0 && !checkIfMinMaxConditionSatisfied(parentIndex, index)) {
            moveUp(index)
        } else {
            moveDown(index)
        }
    }

    /**
     * Remove element from index
     */
    fun remove(index: Int): T {
        if (index + 1 < array.size) {
            val element = array[index]
            val last = array.removeLast()
            array[index] = last
            onIndexModified?.let { it(last, index) }
            moveDown(index)
            return element
        } else {
            return array.removeLast()
        }
    }

    private fun moveUp(index: Int) {
        // check with parent and perform rotation
        val parentIndex = parentIndex(index)
        if (parentIndex >= 0 && !checkIfMinMaxConditionSatisfied(parentIndex, index)) {
            swap(parentIndex, index)
            moveUp(parentIndex)
        }
    }

    private fun swap(left: Int, right: Int) {
        val temp = array[left]
        array[left] = array[right]
        array[right] = temp
        onIndexModified?.let { it(array[left], left) }
        onIndexModified?.let { it(array[right], right) }
    }

    /**
     * If min/max heap condition is satisfied, return true
     */
    private fun checkIfMinMaxConditionSatisfied(left: Int, right: Int): Boolean {
        val result = array[left].compareTo(array[right])
        if (sortByMin) {
            return result <= 0
        } else {
            return result >= 0
        }
    }

    private fun moveDown(parentIndex: Int) {
        val leftChildIndex = firstChild(parentIndex)
        var minIndex = parentIndex
        if (leftChildIndex < array.size) {
            if (!checkIfMinMaxConditionSatisfied(parentIndex, leftChildIndex)) {
                minIndex = leftChildIndex
            }
        }
        val rightChildIndex = leftChildIndex + 1
        if (rightChildIndex < array.size) {
            if (!checkIfMinMaxConditionSatisfied(leftChildIndex, rightChildIndex)) {
                minIndex = rightChildIndex
            }
        }
        if (minIndex != parentIndex) {
            swap(parentIndex, minIndex)
            moveDown(minIndex)
        }
    }

    private fun parentIndex(index: Int): Int {
        return (index - 1) shr 1
    }

    private fun firstChild(index: Int): Int {
        return (index shl 1) or 1
    }


}

