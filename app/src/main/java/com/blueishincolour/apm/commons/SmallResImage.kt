package com.blueishincolour.apm.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.blueishincolour.apm.ApmViewModel
import com.blueishincolour.apm.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SmallResImage(uri:String,
                  vm:ApmViewModel = hiltViewModel()
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }
        .build()

    if(vm.resPictureSuffix == "png") {
        Image(painter =
        rememberImagePainter(
            data = uri.toUri(),
            builder = {
                placeholder(R.drawable.thunder_svgrepo_com)
                error(R.drawable.thunder_svgrepo_com)

            }
        ),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)

                .fillMaxSize(),
            contentDescription = null)
    }
    else {
        val subUri = "https://firebasestorage.googleapis.com/v0/b/meninsuits.appspot.com/o/images%2Fae97a523-5ff4-4bcc-b5e2-26dfbd01aefa.svg?alt=media&token=74c40ca3-d664-474c-a9f6-e1266abb1562".toUri()
        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
            val painter = rememberImagePainter(if(uri.isNotEmpty())uri.toUri()else subUri )

            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)

                    .fillMaxSize(),
                contentDescription = null
            )
        }
    }
}