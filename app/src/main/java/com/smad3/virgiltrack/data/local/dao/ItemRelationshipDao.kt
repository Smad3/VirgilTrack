package com.smad3.virgiltrack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.smad3.virgiltrack.data.local.model.ItemRelationship

@Dao
interface ItemRelationshipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRelationship(relationship: ItemRelationship)
}
