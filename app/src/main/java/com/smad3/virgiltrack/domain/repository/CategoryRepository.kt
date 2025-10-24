package com.smad3.virgiltrack.domain.repository

import com.smad3.virgiltrack.data.local.model.Category
import com.smad3.virgiltrack.data.local.model.TemplateField
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing node *types* (Categories) and their field definitions (TemplateFields).
 */
interface CategoryRepository {

    fun getAllCategories(): Flow<List<Category>>

    suspend fun addCategory(category: Category)

    suspend fun deleteCategory(category: Category)

    fun getFieldsForCategory(categoryId: Long): Flow<List<TemplateField>>

    suspend fun addFieldToCategory(field: TemplateField)
}
