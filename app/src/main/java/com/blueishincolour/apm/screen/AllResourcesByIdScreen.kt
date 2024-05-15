package com.blueishincolour.apm.screen

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.blueishincolour.apm.ApmViewModel
import com.blueishincolour.apm.MainActivity
import com.blueishincolour.apm.Routes
import com.blueishincolour.apm.commons.ApmTonalFilledButton
import com.blueishincolour.apm.commons.ResCreatorTile
import com.blueishincolour.apm.commons.ResListTile
import com.blueishincolour.apm.commons.SmallResImage
import com.blueishincolour.apm.model.ResModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllResourcesByIdScreen(
    scaffoldState: BottomSheetScaffoldState,
    vm: ApmViewModel = hiltViewModel(),
    navController : NavHostController
){
    val context = LocalContext.current
    var listOfRes = remember {
        mutableStateOf<List<ResModel>>(emptyList())
    }
    LaunchedEffect(key1 = 1) {
        vm.fetchAllRes(context, isCompleted = {listOfRes.value = listOfRes.value.plus(it)})
    }


LazyColumn {
    item {
        ApmTonalFilledButton(text = "reload") {
            context.startActivity(Intent(context,MainActivity::class.java))
        }
        ResCreatorTile(
            scaffoldState=scaffoldState,
            vm = vm)
        Spacer(modifier = Modifier.height(20.dp))
    }
    items(listOfRes.value){
        ResListTile(
            res = it,
            navController = navController,
            vm = vm
        )
    }
    item{
        Spacer(modifier = Modifier.height(100.dp))
    }
}
}
