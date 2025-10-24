package com.smad3.virgiltrack.domain.repository

import com.smad3.virgiltrack.data.local.model.*
import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    fun getMediaItemsForCategory(categoryId: Long): Flow<List<MediaItem>>

    fun getMediaItemById(itemId: Long): Flow<MediaItem>

    fun getFieldValuesForItem(itemId: Long): Flow<List<FieldValue>>

    suspend fun addMediaItem(item: MediaItem, values: List<FieldValue>): Long

    fun getSeasonsForItem(itemId: Long): Flow<List<Season>>

    suspend fun addSeason(season: Season)

    fun getEpisodesForSeason(seasonId: Long): Flow<List<Episode>>

    suspend fun updateEpisode(episode: Episode)
}
