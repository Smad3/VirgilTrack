package com.smad3.virgiltrack.domain.repository

import com.smad3.virgiltrack.data.local.model.FieldValue
import com.smad3.virgiltrack.data.local.model.MediaItem
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing the data graph: nodes (MediaItems) and their relationships.
 */
interface MediaRepository {

    /**
     * Gets all root items for a specific category.
     * Root items are items that are not a child of any other item.
     */
    fun getRootItemsForCategory(categoryId: Long): Flow<List<MediaItem>>

    /**
     * Gets all direct children for a given parent item.
     */
    fun getChildItems(parentId: Long): Flow<List<MediaItem>>

    fun getFieldValuesForItem(itemId: Long): Flow<List<FieldValue>>

    /**
     * Adds a new MediaItem as a child of another MediaItem.
     */
    suspend fun addChildItem(
        childItem: MediaItem,
        childFieldValues: List<FieldValue>,
        parentId: Long,
        orderIndex: Int
    )

    /**
     * Adds a new root MediaItem (which has no parent).
     */
    suspend fun addRootItem(item: MediaItem, values: List<FieldValue>)
}
