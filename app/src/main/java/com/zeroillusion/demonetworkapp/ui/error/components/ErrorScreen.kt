package com.zeroillusion.demonetworkapp.ui.error.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zeroillusion.demonetworkapp.domain.utils.Constants.MAIN_SCREEN

@Composable
fun ErrorScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Text(
            text = "No internet connection",
            style = TextStyle.Default.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.W500
            ),
            modifier = Modifier.padding(32.dp)
        )
        IconButton(onClick = {
            navController.navigate(MAIN_SCREEN) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Refresh",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(navController = rememberNavController())
}