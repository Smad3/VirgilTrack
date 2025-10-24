package com.smad3.virgiltrack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.smad3.virgiltrack.data.local.model.Episode
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episode: Episode)

    @Update
    suspend fun updateEpisode(episode: Episode)

    @Query("SELECT * FROM episodes WHERE seasonOwnerId = :seasonOwnerId ORDER BY customOrderIndex ASC, episodeNumber ASC")
    fun getEpisodesForSeason(seasonOwnerId: Long): Flow<List<Episode>>
}
