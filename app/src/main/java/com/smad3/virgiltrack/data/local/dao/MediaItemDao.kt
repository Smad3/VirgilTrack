package com.smad3.virgiltrack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smad3.virgiltrack.data.local.model.MediaItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaItem(item: MediaItem): Long

    @Query("SELECT * FROM media_items WHERE categoryOwnerId = :categoryId")
    fun getMediaItemsForCategory(categoryId: Long): Flow<List<MediaItem>>

    @Query("SELECT * FROM media_items WHERE itemId = :itemId")
    fun getMediaItemById(itemId: Long): Flow<MediaItem>
}
