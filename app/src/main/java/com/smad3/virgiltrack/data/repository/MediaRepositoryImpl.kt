package com.smad3.virgiltrack.data.repository

import com.smad3.virgiltrack.data.local.dao.FieldValueDao
import com.smad3.virgiltrack.data.local.dao.ItemRelationshipDao
import com.smad3.virgiltrack.data.local.dao.MediaItemDao
import com.smad3.virgiltrack.data.local.model.FieldValue
import com.smad3.virgiltrack.data.local.model.ItemRelationship
import com.smad3.virgiltrack.data.local.model.MediaItem
import com.smad3.virgiltrack.data.local.model.relations.MediaItemWithTitle
import com.smad3.virgiltrack.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    private val mediaItemDao: MediaItemDao,
    private val fieldValueDao: FieldValueDao,
    private val itemRelationshipDao: ItemRelationshipDao
) : MediaRepository {

    override fun getRootItemsForCategory(categoryId: Long): Flow<List<MediaItem>> {
        return mediaItemDao.getRootItemsForCategory(categoryId)
    }

    override fun getChildItems(parentId: Long): Flow<List<MediaItem>> {
        return mediaItemDao.getChildItems(parentId)
    }

    override fun getFieldValuesForItem(itemId: Long): Flow<List<FieldValue>> {
        return fieldValueDao.getFieldValuesForItem(itemId)
    }

    override fun getMediaItemsForCategory(categoryId: Long): Flow<List<MediaItemWithTitle>> {
        return mediaItemDao.getMediaItemsWithTitle(categoryId)
    }

    override suspend fun addChildItem(
        childItem: MediaItem,
        childFieldValues: List<FieldValue>,
        parentId: Long,
        orderIndex: Int
    ) {
        val newChildId = mediaItemDao.insertMediaItem(childItem)
        val valuesWithCorrectId = childFieldValues.map { it.copy(itemOwnerId = newChildId) }
        fieldValueDao.insertFieldValues(valuesWithCorrectId)

        val relationship = ItemRelationship(
            parentId = parentId,
            childId = newChildId,
            orderIndex = orderIndex
        )
        itemRelationshipDao.insertRelationship(relationship)
    }

    override suspend fun addRootItem(item: MediaItem, values: List<FieldValue>) {
        val newItemId = mediaItemDao.insertMediaItem(item)
        val valuesWithCorrectId = values.map { it.copy(itemOwnerId = newItemId) }
        fieldValueDao.insertFieldValues(valuesWithCorrectId)
    }
}
