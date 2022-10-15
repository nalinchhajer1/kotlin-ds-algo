package com.nalin.datastructurealgorithm.ds

/**
 * 1. Union (A,B) -> Make A and B part of a group
 * 2. Find (A,B) -> Find if A and B are connected
 * 3. Find Parent (A) -> Find representative of parents
 *
 * Step1: Each node is represented as themself
 * Step2: If they are connected, one become parent for other
 *
 * usecase: node1, node2
 */
class UnionFind<T>(val parents: MutableMap<T, T> = mutableMapOf()) {

    fun union(elem1: T, elem2: T) {
        val left = this.find(elem1)
        val right = this.find(elem2)
        if (left === right) {
            return
        }
        this.parents[right] = left
    }

    fun find(node: T): T {
        if (parents[node] == null) {
            parents[node] = node
        } else if (parents[node] != node) {
            val result = find(parents[node]!!)
            parents[node] = result
            return result
        }
        return parents[node]!!
    }

    fun isConnected(elem1: T, elem2: T): Boolean {
        val left = this.find(elem1)
        val right = this.find(elem2)
        if (left === right) {
            return true
        }
        return false
    }

}