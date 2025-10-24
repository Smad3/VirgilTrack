package com.smad3.virgiltrack.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "media_items",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MediaItem(
    @PrimaryKey(autoGenerate = true)
    val itemId: Long = 0,
    val categoryOwnerId: Long
)
