package com.smad3.virgiltrack.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "seasons",
    foreignKeys = [
        ForeignKey(
            entity = MediaItem::class,
            parentColumns = ["itemId"],
            childColumns = ["itemOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Season(
    @PrimaryKey(autoGenerate = true)
    val seasonId: Long = 0,
    val itemOwnerId: Long,
    val seasonNumber: Int,
    val title: String?
)
