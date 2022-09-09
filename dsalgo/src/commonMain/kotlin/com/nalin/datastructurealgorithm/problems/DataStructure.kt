package com.nalin.datastructurealgorithm.problems

fun findFibonacciSeries(n: Int): MutableList<Int> {
    val result = mutableListOf<Int>()
    var a = 0;
    result.add(a)
    if (n == 0) {
        return result
    }
    var b = 1;
    result.add(b)
    if (n == 1) {
        return result
    }
    var sum = 0;

    for (i in 2 until n) {
        sum = a + b
        a = b
        b = sum
        result.add(sum)
    }
    return result
}

fun primeNumbers(n: Int): List<Int> {
    val array = Array(n + 1) { true }
    array[0] = false
    array[1] = false
    var i = 2;

    while (i <= n) {
        if (array[i]) {
            var j = i + i
            while (j <= n) {
                array[j] = false
                j += i
            }
        }
        i++
    }
    return array.mapIndexed() { index, b -> if (b) index else 0 }.filter { it > 0 }
}

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}

fun lcm(a: Int, b: Int): Int {
    return (a * b) / gcd(a, b)
}

fun binarySearch(array: List<Int>, target: Int): Int {
    // Write your code here.
    fun search(range: IntRange): Int {
        val mid = range.start + ((range.endInclusive - range.start) shr 1)
        if (range.start > mid || range.start > range.endInclusive) return -1;
        if (array[mid] == target) {
            return mid;
        }
        if (array[mid] > target) {
            return search(range.start..mid - 1)
        }
        return search(mid + 1..range.endInclusive)
    }

    return search(0 until array.size)
}