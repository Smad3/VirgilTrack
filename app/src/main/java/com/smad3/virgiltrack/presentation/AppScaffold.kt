package com.smad3.virgiltrack.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.smad3.virgiltrack.data.local.model.Category
import com.smad3.virgiltrack.presentation.categorylist.CategoryListScreen
import com.smad3.virgiltrack.presentation.categorylist.CategoryListViewModel
import com.smad3.virgiltrack.ui.theme.VirgilTrackTheme
import kotlinx.coroutines.launch

/**
 * The stateful version of the scaffold that connects to the ViewModel and manages state.
 */
@Composable
fun AppScaffold() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val categoryViewModel: CategoryListViewModel = hiltViewModel()

    var selectedCategory by remember { mutableStateOf<Category?>(null) }

    AppScaffoldContent(
        drawerState = drawerState,
        title = selectedCategory?.name ?: "VirgilTrack",
        onMenuClick = {
            scope.launch {
                drawerState.open()
            }
        },
        onEditClick = { categoryViewModel.toggleEditMode() },
        drawerContent = {
            CategoryListScreen(
                viewModel = categoryViewModel,
                onCategoryClick = { category ->
                    selectedCategory = category
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (selectedCategory != null) {
                Text(text = "Main content for ${selectedCategory!!.name}")
            } else {
                Text(text = "Welcome! Select a category from the drawer.")
            }
        }
    }
}

/**
 * The stateless, previewable version of the scaffold.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffoldContent(
    drawerState: DrawerState,
    title: String,
    onMenuClick: () -> Unit,
    onEditClick: () -> Unit,
    drawerContent: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                drawerContent()
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(title) },
                    navigationIcon = {
                        IconButton(onClick = onMenuClick) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Open navigation drawer"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = onEditClick) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "Toggle edit mode"
                            )
                        }
                    }
                )
            },
            content = content
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppScaffoldPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    VirgilTrackTheme {
        AppScaffoldContent(
            drawerState = drawerState,
            title = "Preview Title",
            onMenuClick = {},
            onEditClick = {},
            drawerContent = { Text("Drawer Content Preview", modifier = Modifier.padding(16.dp)) }
        ) { paddingValues ->
            Text("Main Content Preview", modifier = Modifier.padding(paddingValues))
        }
    }
}
