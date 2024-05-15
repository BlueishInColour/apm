package com.blueishincolour.apm.commons

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.blueishincolour.apm.ApmViewModel

@Composable
fun ScreenLoadingDialog(
    vm: ApmViewModel = hiltViewModel()
){
    if(vm.isScreenLoading){
        Dialog(onDismissRequest = { vm.changeIsScreenLoading() }) {
            CircularProgressIndicator()
        }
    }
}