package com.zeroillusion.demonetworkapp.ui.main

import com.zeroillusion.demonetworkapp.domain.model.Image

data class MainState(
    val images: List<Image> = emptyList(),
    val isLoading: Boolean = false
)