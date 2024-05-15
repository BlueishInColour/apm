package com.blueishincolour.apm.commons


import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun  ResImagePicker(
    scaffoldState: BottomSheetScaffoldState,
    onImageSelected:(Uri?)->Unit = {},
    picture:Any,
    vm:ApmViewModel = hiltViewModel()
) {

    val context= LocalContext.current
    val scope = rememberCoroutineScope()
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }
        .build()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {uri: Uri? -> onImageSelected( uri)
        }

    )
    Box(
        contentAlignment = Alignment.Center
    )
    {if(vm.resPictureSuffix == "png"){
        Image(  painter =
        rememberImagePainter(
            data = picture,
            builder = {

                placeholder(R.drawable.thunder_svgrepo_com)
                error(R.drawable.thunder_svgrepo_com)

            }
        ),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(400.dp)
                .background(Color.White)
                .fillMaxSize(),
            contentDescription = null)}
        else{

        val subUri = "https://firebasestorage.googleapis.com/v0/b/meninsuits.appspot.com/o/images%2Fae97a523-5ff4-4bcc-b5e2-26dfbd01aefa.svg?alt=media&token=74c40ca3-d664-474c-a9f6-e1266abb1562".toUri()
        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
            val painter = rememberImagePainter(if(vm.resPicture.isNotEmpty())vm.resPicture.toUri()else subUri )

            Image(
                painter = painter,
                contentDescription = "SVG Image",
                modifier = Modifier
                    .size(400.dp),
                contentScale = ContentScale.FillBounds
            )
        }
    }

        //pick from bottom sheeet
        IconButton(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomStart)
                .padding(10.dp)
            ,
            onClick = {
vm.openBottomSheet(context,scaffoldState)
            }) {
            Icon(imageVector = Icons.Outlined.Build,
                tint  = Color.Black,
                contentDescription =null )
        }

        //pick from file
        IconButton(
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.BottomEnd)
                .padding(10.dp)
            ,
            onClick = { launcher.launch("image/*") }) {
            Icon(imageVector = Icons.Outlined.Edit,
                tint  = Color.Black,
                contentDescription =null )
        }
    }
}