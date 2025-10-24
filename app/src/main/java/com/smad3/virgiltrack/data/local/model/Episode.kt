package com.smad3.virgiltrack.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "episodes",
    foreignKeys = [
        ForeignKey(
            entity = Season::class,
            parentColumns = ["seasonId"],
            childColumns = ["seasonOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Episode(
    @PrimaryKey(autoGenerate = true)
    val episodeId: Long = 0,
    val seasonOwnerId: Long,
    val episodeNumber: Int,
    val customOrderIndex: Int,
    val isWatched: Boolean,
    val title: String?
)
