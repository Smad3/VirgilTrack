package com.smad3.virgiltrack.presentation.categorylist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.smad3.virgiltrack.data.local.model.Category
import com.smad3.virgiltrack.ui.theme.VirgilTrackTheme

/**
 * Stateful version that connects to the ViewModel
 */
@Composable
fun CategoryListScreen(
    viewModel: CategoryListViewModel = hiltViewModel(),
    onCategoryClick: (Category) -> Unit
) {
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val isEditMode by viewModel.isEditMode.collectAsStateWithLifecycle()

    CategoryListContent(
        categories = categories,
        isEditMode = isEditMode,
        onCategoryClick = onCategoryClick,
        onAddCategory = {
            val newCategoryName = "Movies ${System.currentTimeMillis() % 100}"
            viewModel.addCategory(newCategoryName)
        },
        onDeleteCategory = {
            viewModel.deleteCategory(it)
        }
    )
}

/**
 * Stateless, previewable version
 */
@Composable
fun CategoryListContent(
    categories: List<Category>,
    isEditMode: Boolean,
    onCategoryClick: (Category) -> Unit,
    onAddCategory: () -> Unit,
    onDeleteCategory: (Category) -> Unit
) {
    var categoryToDelete by remember { mutableStateOf<Category?>(null) }

    if (categoryToDelete != null) {
        AlertDialog(
            onDismissRequest = { categoryToDelete = null },
            title = { Text("Delete Category") },
            text = { Text("Are you sure you want to delete '${categoryToDelete!!.name}'? This action cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteCategory(categoryToDelete!!)
                        categoryToDelete = null
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { categoryToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Categories", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (categories.isEmpty()) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "No categories yet. Add one!")
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(categories, key = { it.categoryId }) { category ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onCategoryClick(category) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = category.name, modifier = Modifier.weight(1f).padding(8.dp))
                        AnimatedVisibility(visible = isEditMode) {
                            IconButton(onClick = { categoryToDelete = category }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onAddCategory) {
            Text("Add Category")
        }
    }
}


@Preview(name = "Category List - Populated", showBackground = true)
@Composable
fun CategoryListPopulatedPreview() {
    VirgilTrackTheme {
        CategoryListContent(
            categories = listOf(
                Category(1, "Movies"),
                Category(2, "TV Shows"),
                Category(3, "Books")
            ),
            isEditMode = true,
            onCategoryClick = {},
            onAddCategory = {},
            onDeleteCategory = {}
        )
    }
}

@Preview(name = "Category List - Empty", showBackground = true)
@Composable
fun CategoryListEmptyPreview() {
    VirgilTrackTheme {
        CategoryListContent(
            categories = emptyList(),
            isEditMode = false,
            onCategoryClick = {},
            onAddCategory = {},
            onDeleteCategory = {}
        )
    }
}
