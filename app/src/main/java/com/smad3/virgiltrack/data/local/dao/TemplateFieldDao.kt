package com.smad3.virgiltrack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smad3.virgiltrack.data.local.model.TemplateField
import kotlinx.coroutines.flow.Flow

@Dao
interface TemplateFieldDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertField(field: TemplateField)

    @Query("SELECT * FROM template_fields WHERE categoryOwnerId = :categoryId ORDER BY fieldName ASC")
    fun getFieldsForCategory(categoryId: Long): Flow<List<TemplateField>>
}
