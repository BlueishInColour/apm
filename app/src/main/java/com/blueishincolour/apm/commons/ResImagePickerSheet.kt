package com.blueishincolour.apm.commons

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.blueishincolour.apm.ApmViewModel
import com.blueishincolour.apm.R

@Composable
fun PickImageSheet(vm : ApmViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val listOfImagesUri = remember {
        mutableStateOf<List<Uri>>(emptyList())
    }
    val cursor = getAllMediaFilesCursor(context)
    if(true == cursor?.moveToFirst()){

        val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

        do {
            val id = idColumn.let { cursor.getLong(it) }
            val contentUri = ContentUris.appendId(
                MediaStore.Files.getContentUri("external").buildUpon(),
                id
            ).build()
            listOfImagesUri.value.plus(contentUri)

        }while (cursor.moveToNext())

    }
    cursor?.close()

    LazyVerticalStaggeredGrid(
        modifier = Modifier.height(500.dp),
        columns = StaggeredGridCells.Fixed(4)) {
        items(listOfImagesUri.value){
            Image(  painter =
            rememberImagePainter(
                data = it,
                builder = {

                    placeholder(R.drawable.thunder_svgrepo_com)
                    error(R.drawable.thunder_svgrepo_com)

                }
            ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
//                    .size(70.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)

                    .fillMaxSize(),
                contentDescription = null)        }

    }


}

private fun getAllMediaFilesCursor(context:Context): Cursor? {
    val projections =
        arrayOf(
            MediaStore.Images.Media.DATA,

        )

    val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL)
    } else {
        MediaStore.Files.getContentUri("external")
    }

    return context.contentResolver.query(
        collection,
        projections,
        null,
        null,
        null
    )
}
