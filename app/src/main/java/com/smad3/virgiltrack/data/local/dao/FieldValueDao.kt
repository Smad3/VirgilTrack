package com.smad3.virgiltrack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smad3.virgiltrack.data.local.model.FieldValue
import kotlinx.coroutines.flow.Flow

@Dao
interface FieldValueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFieldValues(values: List<FieldValue>)

    @Query("SELECT * FROM field_values WHERE itemOwnerId = :itemOwnerId")
    fun getFieldValuesForItem(itemOwnerId: Long): Flow<List<FieldValue>>
}
