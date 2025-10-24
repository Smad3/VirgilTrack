package com.smad3.virgiltrack.presentation.categorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smad3.virgiltrack.data.local.model.Category
import com.smad3.virgiltrack.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val categories: StateFlow<List<Category>> = categoryRepository.getAllCategories()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addCategory(name: String, hasHierarchicalTracking: Boolean) {
        viewModelScope.launch {
            val newCategory = Category(
                name = name,
                hasHierarchicalTracking = hasHierarchicalTracking
            )
            categoryRepository.addCategory(newCategory)
        }
    }
}
