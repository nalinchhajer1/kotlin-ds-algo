package com.nalin.datastructurealgorithm

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
