package com.zeroillusion.demonetworkapp.ui.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zeroillusion.demonetworkapp.domain.utils.Constants.ERROR_SCREEN
import com.zeroillusion.demonetworkapp.ui.main.MainViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.state

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            LaunchedEffect(key1 = true) {
                viewModel.eventFlow.collectLatest { event ->
                    when (event) {
                        is MainViewModel.UIEvent.ShowSnackbar -> {
                            snackbarHostState.showSnackbar(
                                message = event.message
                            )
                        }
                        is MainViewModel.UIEvent.NavigateToErrorScreen -> {
                            navController.navigate(ERROR_SCREEN) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.images.size) { index ->
                        ImageCard(
                            image = state.images[index],
                            imageLoader = viewModel.imageLoader
                        )
                    }
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )
}