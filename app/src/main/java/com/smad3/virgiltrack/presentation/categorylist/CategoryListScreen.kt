package com.smad3.virgiltrack.presentation.categorylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CategoryListScreen(
    viewModel: CategoryListViewModel = hiltViewModel()
) {
    val categories by viewModel.categories.collectAsStateWithLifecycle()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (categories.isEmpty()) {
                Text(text = "No categories yet. Add one!")
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(categories) { category ->
                        Text(text = category.name)
                    }
                }
            }

            Button(onClick = { 
                // For demonstration, adds a new category with a random name
                val newCategoryName = "Movies ${System.currentTimeMillis() % 100}"
                viewModel.addCategory(newCategoryName, false)
            }) {
                Text("Add Category")
            }
        }
    }
}
