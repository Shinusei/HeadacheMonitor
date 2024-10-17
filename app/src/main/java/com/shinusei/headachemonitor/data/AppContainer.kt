package com.shinusei.headachemonitor.data

import android.content.Context

interface AppContainer {
    val headacheRepository: HaedachesRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineHeadachesRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [HaedachesRepository]
     */
    override val headacheRepository: HaedachesRepository by lazy{
        OfflineHeadachesRepository(HeadacheDatabase.getDatabase(context).headacheDao())
    }
}