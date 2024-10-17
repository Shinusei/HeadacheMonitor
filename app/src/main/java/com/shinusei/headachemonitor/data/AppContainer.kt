package com.shinusei.headachemonitor.data

import android.content.Context

interface AppContainer {
    val headacheRepository: HeadachesRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineHeadachesRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [HeadachesRepository]
     */
    override val headacheRepository: HeadachesRepository by lazy{
        OfflineHeadachesRepository(HeadacheDatabase.getDatabase(context).headacheDao())
    }
}