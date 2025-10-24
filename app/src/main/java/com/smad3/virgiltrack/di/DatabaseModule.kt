package com.smad3.virgiltrack.di

import android.content.Context
import androidx.room.Room
import com.smad3.virgiltrack.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideCategoryDao(db: AppDatabase) = db.categoryDao()

    @Provides
    @Singleton
    fun provideTemplateFieldDao(db: AppDatabase) = db.templateFieldDao()

    @Provides
    @Singleton
    fun provideMediaItemDao(db: AppDatabase) = db.mediaItemDao()

    @Provides
    @Singleton
    fun provideFieldValueDao(db: AppDatabase) = db.fieldValueDao()

    @Provides
    @Singleton
    fun provideSeasonDao(db: AppDatabase) = db.seasonDao()

    @Provides
    @Singleton
    fun provideEpisodeDao(db: AppDatabase) = db.episodeDao()

}
