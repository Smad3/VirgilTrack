package com.smad3.virgiltrack.domain.repository

import com.smad3.virgiltrack.data.local.model.Category
import com.smad3.virgiltrack.data.local.model.TemplateField
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getAllCategories(): Flow<List<Category>>

    fun getCategoryById(categoryId: Long): Flow<Category>

    suspend fun addCategory(category: Category)

    fun getFieldsForCategory(categoryId: Long): Flow<List<TemplateField>>

    suspend fun addFieldToCategory(field: TemplateField)
}
