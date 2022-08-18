package com.oolong.screentimer20.di

import android.content.Context
import com.oolong.screentimer20.data.LocalDurationDataRepositoryImpl
import com.oolong.screentimer20.data.durationDataDataStore
import com.oolong.screentimer20.domain.IDurationDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDurationData(
        @ApplicationContext app: Context
    ): IDurationDataRepository = LocalDurationDataRepositoryImpl(app.durationDataDataStore)
}