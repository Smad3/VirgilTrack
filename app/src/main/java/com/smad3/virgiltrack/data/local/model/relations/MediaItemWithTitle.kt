package com.smad3.virgiltrack.data.local.model.relations

/**
 * A data class to hold the result of a JOIN query.
 * It represents a media item with its title value.
 */
data class MediaItemWithTitle(
    val itemId: Long,
    val title: String
)
