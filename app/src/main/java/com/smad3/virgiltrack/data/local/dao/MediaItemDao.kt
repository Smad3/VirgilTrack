package com.smad3.virgiltrack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smad3.virgiltrack.data.local.model.MediaItem
import com.smad3.virgiltrack.data.local.model.relations.MediaItemWithTitle
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaItem(item: MediaItem): Long

    @Query("SELECT * FROM media_items WHERE itemId = :itemId")
    fun getMediaItemById(itemId: Long): Flow<MediaItem?>

    @Query("""
        SELECT i.* FROM media_items as i
        INNER JOIN item_relationships as r ON i.itemId = r.childId
        WHERE r.parentId = :parentId
        ORDER BY r.orderIndex ASC
    """)
    fun getChildItems(parentId: Long): Flow<List<MediaItem>>

    @Query("""
        SELECT * FROM media_items 
        WHERE categoryOwnerId = :categoryId AND itemId NOT IN (SELECT childId FROM item_relationships)
    """)
    fun getRootItemsForCategory(categoryId: Long): Flow<List<MediaItem>>
    
    @Query("""
        SELECT mi.itemId, fv.value as title
        FROM media_items mi
        JOIN field_values fv ON mi.itemId = fv.itemOwnerId
        JOIN template_fields tf ON fv.fieldOwnerId = tf.fieldId
        WHERE mi.categoryOwnerId = :categoryId AND tf.isTitle = 1
    """)
    fun getMediaItemsWithTitle(categoryId: Long): Flow<List<MediaItemWithTitle>>
}
