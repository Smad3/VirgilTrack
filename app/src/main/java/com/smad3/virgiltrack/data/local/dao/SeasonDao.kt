package com.smad3.virgiltrack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smad3.virgiltrack.data.local.model.Season
import kotlinx.coroutines.flow.Flow

@Dao
interface SeasonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeason(season: Season)

    @Query("SELECT * FROM seasons WHERE itemOwnerId = :itemOwnerId ORDER BY seasonNumber ASC")
    fun getSeasonsForItem(itemOwnerId: Long): Flow<List<Season>>
}
