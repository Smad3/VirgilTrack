package com.smad3.virgiltrack.presentation.medialist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smad3.virgiltrack.data.local.model.relations.MediaItemWithTitle
import com.smad3.virgiltrack.domain.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MediaListViewModel @Inject constructor(
    mediaRepository: MediaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val categoryId: StateFlow<Long> = savedStateHandle.getStateFlow("categoryId", -1L)

    val mediaItems: StateFlow<List<MediaItemWithTitle>> = categoryId.flatMapLatest { id ->
        if (id != -1L) {
            mediaRepository.getMediaItemsForCategory(id)
        } else {
            flowOf(emptyList()) // Return an empty flow if ID is invalid
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}
