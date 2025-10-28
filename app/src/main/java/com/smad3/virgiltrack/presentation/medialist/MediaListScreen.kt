package com.smad3.virgiltrack.presentation.medialist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.smad3.virgiltrack.data.local.model.relations.MediaItemWithTitle
import com.smad3.virgiltrack.ui.theme.VirgilTrackTheme

@Composable
fun MediaListScreen(
    viewModel: MediaListViewModel = hiltViewModel()
) {
    val mediaItems by viewModel.mediaItems.collectAsStateWithLifecycle()

    MediaListContent(
        mediaItems = mediaItems,
        onAddItemClick = { /* TODO */ }
    )
}

@Composable
fun MediaListContent(
    mediaItems: List<MediaItemWithTitle>,
    onAddItemClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddItemClick) {
                Icon(Icons.Default.Add, contentDescription = "Add new item")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(mediaItems) { item ->
                    Text(
                        text = item.title,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MediaListScreenPreview() {
    VirgilTrackTheme {
        MediaListContent(
            mediaItems = listOf(
                MediaItemWithTitle(1, "The Fellowship of the Ring"),
                MediaItemWithTitle(2, "The Two Towers"),
                MediaItemWithTitle(3, "The Return of the King")
            ),
            onAddItemClick = {}
        )
    }
}
