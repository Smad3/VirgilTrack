package com.smad3.virgiltrack.data.repository

import com.smad3.virgiltrack.data.local.dao.CategoryDao
import com.smad3.virgiltrack.data.local.dao.TemplateFieldDao
import com.smad3.virgiltrack.data.local.model.Category
import com.smad3.virgiltrack.data.local.model.TemplateField
import com.smad3.virgiltrack.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val templateFieldDao: TemplateFieldDao
) : CategoryRepository {

    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories()
    }

    override suspend fun addCategory(category: Category) {
        categoryDao.insertCategory(category)
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }

    override fun getFieldsForCategory(categoryId: Long): Flow<List<TemplateField>> {
        return templateFieldDao.getFieldsForCategory(categoryId)
    }

    override suspend fun addFieldToCategory(field: TemplateField) {
        templateFieldDao.insertField(field)
    }
}
