package com.smad3.virgiltrack.data.repository

import com.smad3.virgiltrack.data.local.dao.EpisodeDao
import com.smad3.virgiltrack.data.local.dao.FieldValueDao
import com.smad3.virgiltrack.data.local.dao.MediaItemDao
import com.smad3.virgiltrack.data.local.dao.SeasonDao
import com.smad3.virgiltrack.data.local.model.*
import com.smad3.virgiltrack.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaItemDao: MediaItemDao,
    private val fieldValueDao: FieldValueDao,
    private val seasonDao: SeasonDao,
    private val episodeDao: EpisodeDao
) : MediaRepository {

    override fun getMediaItemsForCategory(categoryId: Long): Flow<List<MediaItem>> {
        return mediaItemDao.getMediaItemsForCategory(categoryId)
    }

    override fun getMediaItemById(itemId: Long): Flow<MediaItem> {
        return mediaItemDao.getMediaItemById(itemId)
    }

    override fun getFieldValuesForItem(itemId: Long): Flow<List<FieldValue>> {
        return fieldValueDao.getFieldValuesForItem(itemId)
    }

    override suspend fun addMediaItem(item: MediaItem, values: List<FieldValue>): Long {
        val newItemId = mediaItemDao.insertMediaItem(item)
        val valuesWithCorrectId = values.map { it.copy(itemOwnerId = newItemId) }
        fieldValueDao.insertFieldValues(valuesWithCorrectId)
        return newItemId
    }

    override fun getSeasonsForItem(itemId: Long): Flow<List<Season>> {
        return seasonDao.getSeasonsForItem(itemId)
    }

    override suspend fun addSeason(season: Season) {
        seasonDao.insertSeason(season)
    }

    override fun getEpisodesForSeason(seasonId: Long): Flow<List<Episode>> {
        return episodeDao.getEpisodesForSeason(seasonId)
    }

    override suspend fun updateEpisode(episode: Episode) {
        episodeDao.updateEpisode(episode)
    }
}
