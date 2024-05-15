package com.blueishincolour.apm.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.blueishincolour.apm.ApmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResCreatorTile(
    scaffoldState: BottomSheetScaffoldState,
    vm: ApmViewModel = hiltViewModel()
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){

        val context = LocalContext.current
        //picture

        ResImagePicker(
            picture = vm.resPicture,
            onImageSelected = {
                if (it != null) {
                    vm.setImage(context,uri = it)
                }
            },
            vm = vm,
            scaffoldState = scaffoldState
        )
        //res picture suffix
        ApmTextField(hintText = "suffix", value = vm.resPictureSuffix) {
            vm.changeResPictureSuffix(it)
        }
        //id name
        ApmTextField(hintText = "id", value = vm.resId) {
            vm.changeResId(it)
        }
        //picture

        //title
        ApmTextField(hintText = "title", value = vm.resTitle) {
            vm.changeResTitle(it)
        }
        //story
        ApmTextField(hintText = "story", value = vm.resStory) {
            vm.changeResStory(it)
        }
        // create button
        ApmTonalFilledButton(text = "set") {
            vm.setRes(context)
        }
    }
}