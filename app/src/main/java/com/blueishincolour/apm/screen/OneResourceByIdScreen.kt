package com.blueishincolour.apm.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.blueishincolour.apm.ApmViewModel
import com.blueishincolour.apm.commons.ResCreatorTile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneResourcesByIdScreen(
    scaffoldState: BottomSheetScaffoldState,
    vm: ApmViewModel = hiltViewModel(),
    navController : NavHostController
){

    val context = LocalContext.current
    LazyColumn {

        item{
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            ResCreatorTile(
                scaffoldState = scaffoldState,
                vm = vm
            )

            Spacer(modifier = Modifier.height(250.dp))

            FilledTonalButton(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.deleteRes(context = context,resId = vm.resId,navController =navController) }) {
                Text("delete")
            }

        }
    }
}