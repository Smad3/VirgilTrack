package com.smad3.virgiltrack.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    val itemOwnerId: Long,
    val fieldOwnerId: Long,
    val value: String
)
