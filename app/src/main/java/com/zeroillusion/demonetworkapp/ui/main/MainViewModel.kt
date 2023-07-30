package com.zeroillusion.demonetworkapp.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import com.zeroillusion.demonetworkapp.domain.use_case.GetImages
import com.zeroillusion.demonetworkapp.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getImages: GetImages,
    val imageLoader: ImageLoader
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    private var getImagesJob: Job? = null

    init {
        getImagesForMainScreen()
    }

    private fun getImagesForMainScreen() {
        getImagesJob?.cancel()
        getImagesJob = viewModelScope.launch {
            getImages().onEach { result ->
                delay(500L)
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            images = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            images = result.data ?: emptyList(),
                            isLoading = false
                        )
                        if (result.data?.isEmpty() == true) {
                            _eventFlow.emit(
                                UIEvent.NavigateToErrorScreen
                            )
                        } else {
                            _eventFlow.emit(
                                UIEvent.ShowSnackbar(
                                    result.message ?: "Unknown error"
                                )
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            images = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
        object NavigateToErrorScreen : UIEvent()
    }
}