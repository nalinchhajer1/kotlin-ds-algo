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

/**
Note: arrayTo have arrayFrom.size empty space at last

We have two sorted arrays of integers
A has empty slots at the end of it. It has exactly as many empty slots as there are elements in B.
Your goal is to merge the elements from B to A that array contains all of the elements in sorted order. Optimise for speed and memory usage.
 **/
fun mergeSortedArray(arrayTo: Array<Int?>, arrayFrom: Array<Int>): Array<Int?> {
    // TODO: check for em
    if (arrayFrom.size == 0) {
        // then nothing to merge
        return arrayTo
    }


    var pointer = arrayTo.size - 1 // 2
    var pointerTo = arrayTo.size - arrayFrom.size - 1 // -1
    var pointerFrom = arrayFrom.size - 1 // 2

    while (pointer >= 0) {
        if (pointerFrom < 0) {
            break;
        }
        if (pointerTo >= 0 && arrayTo[pointerTo]!! > arrayFrom[pointerFrom]) {
            arrayTo[pointer] = arrayTo[pointerTo]
            pointerTo--
        } else {
            arrayTo[pointer] = arrayFrom[pointerFrom]
            pointerFrom--
        }
        pointer--

        // fast forward
        if (pointerTo < 0 && pointerFrom > 0) {
            while (pointerFrom >= 0) {
                arrayTo[pointer] = arrayFrom[pointerFrom]
                pointerFrom--
                pointer--
            }
        }
    }

    return arrayTo
}


/*
We have a game in which consecutive duplicate pieces of the same type cancel each other out and remaining pieces slide in, until no more pieces can be removed.
Given a board, represented by a string, return the final state of the board after playing the game.

Example 1:
Input: "abbba"
Output: "" ("abbba" => "aa" => "")

Example 2:
Input: "ab"
Output: "ab"
 */
fun mergeDuplicateChar(str: String): String {
    val stack = ArrayDeque<Char>()
    var foundDuplicate = false
    var pointer = 0

    while (pointer < str.length) {
        if (stack.lastOrNull() == str[pointer]) {
            foundDuplicate = true
        } else {
            if (foundDuplicate == true) {
                stack.removeLast()
                foundDuplicate = false
                continue
            }
            stack.addLast(str[pointer])
        }
        if (foundDuplicate && pointer == str.length - 1) {
            stack.removeLast()
            break
        }
        pointer++
    }

    return stack.joinToString("")
}


/**
Given a string S consisting of lowercase English characters, determine if you can make it a palindrome by removing at most 1 character.

tacocats --> True  # tacocats --> tacocat
racercar --> True  # racercar --> racecar, racrcar
kbayak --> True  # kbayak --> kayak
acbccba --> True # acbccba --> abccba
abccbca --> True # abccbca --> abccba

abcd --> False
btnnure --> False
 */
fun findPalindomeWithErrorProbabaility1(
    str: String,
    startPos: Int? = null,
    endPos: Int? = null,
    isForced: Boolean = false
): Boolean {

    var startPointer = startPos ?: 0
    var endPointer = endPos ?: (str.length - 1)

    while (startPointer <= endPointer) {
        if (str[startPointer] == str[endPointer]) {
            if (startPointer == endPointer || startPointer == endPointer + 1) {
                return true
            }
            startPointer++
            endPointer--
        } else {
            // break into 2
            if (isForced) {
                return false
            } else if (findPalindomeWithErrorProbabaility1(
                    str,
                    startPointer + 1,
                    endPointer,
                    true
                )
            ) {
                return true
            } else return findPalindomeWithErrorProbabaility1(
                str,
                startPointer,
                endPointer - 1,
                true
            )

        }

    }
    return true;
}

/**
Given a sequence of non-negative integers and an integer total target, return whether a continuous sequence of integers sums up to target.

[1, 3, 1, 4, 23], 8 : True (because 3 + 1 + 4 = 8)
[1, 3, 1, 4, 23], 7 : False
 */

// [1, 3, 1, 4, 23, 5, 6], 11
// [1,2, 10, 23]

// windowing ->
// if element to add > target else try sink the window

// if window size < taget move to next element
// if next element > target -> ignore the windo


// [1, 3, 1, 4, 23, 5, 6], 11
fun sumOfConsecutiveElement(array: List<Int>, target: Int): Boolean {
    var windowSum = 0 // 9
    var windowStart = 0 // 0
    var windowEnd = 0 // 4

    while (windowStart < array.size) {
        val nextElement = array[windowEnd]
        if (nextElement > target) {
            // moving to next set
            windowStart = windowEnd + 1
            windowEnd = windowStart
            windowSum = 0
            if (windowEnd >= array.size) {
                break;
            }
        }

        if (windowSum + array[windowEnd] == target) {
            return true;
        }
        if (windowSum + array[windowEnd] < target) {
            // increase
            windowSum += array[windowEnd]
            windowEnd++
            if (windowEnd >= array.size) {
                break;
            }
        } else {
            // sink
            if (windowStart + 1 < windowEnd) {
                windowSum -= array[windowStart]
                windowStart++
            }
        }

    }

    return false

}

