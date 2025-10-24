package com.smad3.virgiltrack.data.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Defines a custom field for a given Category. Example: Category "Movie" has a TemplateField "Director".
 */
@Entity(
    tableName = "template_fields",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryOwnerId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TemplateField(
    @PrimaryKey(autoGenerate = true)
    val fieldId: Long = 0,
    val categoryOwnerId: Long, // Foreign key to Category
    val fieldName: String,
    val fieldType: FieldType, // Enum: TEXT, NUMBER, DATE, BOOLEAN
    val isTitle: Boolean
)
