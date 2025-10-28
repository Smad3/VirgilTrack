package com.smad3.virgiltrack.domain.repository

import com.smad3.virgiltrack.data.local.model.FieldValue
import com.smad3.virgiltrack.data.local.model.MediaItem
import com.smad3.virgiltrack.data.local.model.relations.MediaItemWithTitle
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing the data graph: nodes (MediaItems) and their relationships.
 */
interface MediaRepository {

    fun getRootItemsForCategory(categoryId: Long): Flow<List<MediaItem>>

    fun getChildItems(parentId: Long): Flow<List<MediaItem>>

    fun getFieldValuesForItem(itemId: Long): Flow<List<FieldValue>>

    fun getMediaItemsForCategory(categoryId: Long): Flow<List<MediaItemWithTitle>>

    suspend fun addChildItem(
        childItem: MediaItem,
        childFieldValues: List<FieldValue>,
        parentId: Long,
        orderIndex: Int
    )

    suspend fun addRootItem(item: MediaItem, values: List<FieldValue>)
}
