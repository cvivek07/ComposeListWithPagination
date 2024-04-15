package com.vivekchoudhary.listapp.utils

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}