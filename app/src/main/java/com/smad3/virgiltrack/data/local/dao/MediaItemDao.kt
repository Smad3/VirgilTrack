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

    @Query("SELECT * FROM media_items WHERE itemId = :itemId")
    fun getMediaItemById(itemId: Long): Flow<MediaItem?>

    /**
     * Gets all direct children of a given parent item, ordered by the specified index.
     * This is a key query for the new graph architecture.
     */
    @Query("""
        SELECT i.* FROM media_items as i
        INNER JOIN item_relationships as r ON i.itemId = r.childId
        WHERE r.parentId = :parentId
        ORDER BY r.orderIndex ASC
    """)
    fun getChildItems(parentId: Long): Flow<List<MediaItem>>

    /**
     * Gets root items (items that are not a child of any other item) of a specific category.
     * Useful for displaying top-level items like "Movies" or "TV Shows".
     */
    @Query("""
        SELECT * FROM media_items 
        WHERE categoryOwnerId = :categoryId AND itemId NOT IN (SELECT childId FROM item_relationships)
    """)
    fun getRootItemsForCategory(categoryId: Long): Flow<List<MediaItem>>
}
