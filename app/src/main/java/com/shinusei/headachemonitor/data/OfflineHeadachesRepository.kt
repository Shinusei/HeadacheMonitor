package com.shinusei.headachemonitor.data

import kotlinx.coroutines.flow.Flow

class OfflineHeadachesRepository(private val headacheDao: HeadacheDao) : HeadachesRepository {
    override fun getAllItemsStream(): Flow<List<Headache>> = headacheDao.getAllItems()

    override suspend fun insertItem(item: Headache) = headacheDao.insert(item)

    override suspend fun deleteItem(item: Headache) = headacheDao.delete(item)

    override suspend fun updateItem(item: Headache) = headacheDao.update(item)
}
