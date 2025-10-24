package com.smad3.virgiltrack.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Describes a type of node. Examples: "Movie", "TV Show", "Season", "Episode"
 */
@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long = 0,
    val name: String
)
