package com.smad3.virgiltrack.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * The core of the new architecture. This table links any two MediaItems together,
 * creating a parent-child relationship. This allows for infinite hierarchies.
 * Example: A "TV Show" MediaItem is a parent to a "Season 1" MediaItem.
 */
@Entity(
    tableName = "item_relationships",
    primaryKeys = ["parentId", "childId"],
    foreignKeys = [
        ForeignKey(
            entity = MediaItem::class,
            parentColumns = ["itemId"],
            childColumns = ["parentId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MediaItem::class,
            parentColumns = ["itemId"],
            childColumns = ["childId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ItemRelationship(
    val parentId: Long, // FK to MediaItem
    val childId: Long,  // FK to MediaItem
    val orderIndex: Int // For custom sorting, e.g., episode number or track number
)
