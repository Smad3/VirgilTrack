package com.smad3.virgiltrack.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Stores the actual data for a custom field, linked to a specific MediaItem.
 * Example: For MediaItem "Dune", this links to TemplateField "Director" and has value "Denis Villeneuve".
 */
@Entity(
    tableName = "field_values",
    foreignKeys = [
        ForeignKey(
            entity = MediaItem::class,
            parentColumns = ["itemId"],
            childColumns = ["itemOwnerId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TemplateField::class,
            parentColumns = ["fieldId"],
            childColumns = ["fieldOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FieldValue(
    @PrimaryKey(autoGenerate = true)
    val valueId: Long = 0,
    val itemOwnerId: Long, // Foreign key to MediaItem
    val fieldOwnerId: Long, // Foreign key to TemplateField
    val value: String // The actual value, always stored as text.
)
