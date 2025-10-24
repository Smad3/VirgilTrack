package com.smad3.virgiltrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.smad3.virgiltrack.presentation.categorylist.CategoryListScreen
import com.smad3.virgiltrack.ui.theme.VirgilTrackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VirgilTrackTheme {
                CategoryListScreen()
            }
        }
    }
}
