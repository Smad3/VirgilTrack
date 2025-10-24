package com.smad3.virgiltrack.di

import com.smad3.virgiltrack.data.repository.CategoryRepositoryImpl
import com.smad3.virgiltrack.data.repository.MediaRepositoryImpl
import com.smad3.virgiltrack.domain.repository.CategoryRepository
import com.smad3.virgiltrack.domain.repository.MediaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindMediaRepository(impl: MediaRepositoryImpl): MediaRepository

}
