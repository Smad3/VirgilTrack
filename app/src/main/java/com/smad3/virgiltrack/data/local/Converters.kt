package com.smad3.virgiltrack.data.local

import androidx.room.TypeConverter
import com.smad3.virgiltrack.data.local.model.FieldType

class Converters {
    @TypeConverter
    fun fromFieldType(value: FieldType): String = value.name

    @TypeConverter
    fun toFieldType(value: String): FieldType = FieldType.valueOf(value)
}
