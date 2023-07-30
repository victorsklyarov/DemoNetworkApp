package com.zeroillusion.demonetworkapp.ui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.zeroillusion.demonetworkapp.domain.model.Image

@Composable
fun ImageCard(
    image: Image,
    imageLoader: ImageLoader,
    modifier: Modifier = Modifier
) {
    val tags = image.tags.split(", ".toRegex())

    Card(
        modifier = modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = image.url,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                imageLoader = imageLoader
            )
            LazyRow(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                items(tags.size) { index ->
                    Text(
                        text = tags[index],
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .background(
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(vertical = 2.dp, horizontal = 8.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}