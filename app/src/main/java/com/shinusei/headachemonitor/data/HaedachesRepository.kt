package com.shinusei.headachemonitor.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Headache] from a given data source.
 */
interface HaedachesRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<Headache>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: UShort): Flow<Headache?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: Headache)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: Headache)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: Headache)
}
