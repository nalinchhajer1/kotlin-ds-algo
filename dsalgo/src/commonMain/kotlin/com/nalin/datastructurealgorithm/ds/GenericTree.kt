package com.nalin.datastructurealgorithm.ds

interface GenericTree<out T> {
    fun root(): GenericTreeNode<T>?
}

interface GenericTreeNode<out T> {
    fun value(): T
    fun children(): Set<GenericTreeNode<T>>?
}

class MultiChildrenTreeNode<T>(val value: T, var children: HashSet<GenericTreeNode<T>>? = null) :
    GenericTreeNode<T> {

    override fun value(): T {
        return value
    }

    override fun children(): Set<GenericTreeNode<T>>? = children

    fun addChild(node: GenericTreeNode<T>) = safeChild().add(node)

    fun removeChild(node: GenericTreeNode<T>) = children?.remove(node)

    fun childrenSize() = children?.size ?: 0

    private fun safeChild(): HashSet<GenericTreeNode<T>> {
        if (children === null) {
            children = HashSet();
        }
        return children!!
    }
}

class MultiChildrenTree<T>(var root: MultiChildrenTreeNode<T>? = null) : GenericTree<T> {


    override fun root(): GenericTreeNode<T>? {
        return root
    }

}