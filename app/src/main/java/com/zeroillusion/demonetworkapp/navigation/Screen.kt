package com.zeroillusion.demonetworkapp.navigation

import com.zeroillusion.demonetworkapp.domain.utils.Constants.ERROR_SCREEN
import com.zeroillusion.demonetworkapp.domain.utils.Constants.MAIN_SCREEN

sealed class Screen(val route: String) {

    object MainScreen : Screen(MAIN_SCREEN)
    object ErrorScreen : Screen(ERROR_SCREEN)
}
