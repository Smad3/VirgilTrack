package com.smad3.virgiltrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.smad3.virgiltrack.data.local.dao.*
import com.smad3.virgiltrack.data.local.model.*

@Database(
    entities = [
        Category::class,
        TemplateField::class,
        MediaItem::class,
        FieldValue::class,
        ItemRelationship::class // The new relationship table
    ],
    version = 2, // Incremented version after major schema change
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun templateFieldDao(): TemplateFieldDao
    abstract fun mediaItemDao(): MediaItemDao
    abstract fun fieldValueDao(): FieldValueDao
    abstract fun itemRelationshipDao(): ItemRelationshipDao // The new DAO

    companion object {
        const val DATABASE_NAME = "virgil_track_db"
    }
}
