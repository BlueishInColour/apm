package com.blueishincolour.apm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blueishincolour.apm.commons.PickImageSheet
import com.blueishincolour.apm.commons.ScreenLoadingDialog
import com.blueishincolour.apm.screen.AllResourcesByIdScreen
import com.blueishincolour.apm.screen.OneResourcesByIdScreen
import com.blueishincolour.apm.ui.theme.APMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          val  vm: ApmViewModel = hiltViewModel()
            val navController = rememberNavController()
            val state = rememberBottomSheetScaffoldState()
            APMTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    BottomSheetScaffold(
                        sheetPeekHeight = 0.dp,

                        scaffoldState = state,
                        sheetContent = {
                            PickImageSheet(vm = vm)
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Routes.AllScreenByIdScreen.route
                        ) {
                            composable(Routes.AllScreenByIdScreen.route) {
                                AllResourcesByIdScreen(
                                    scaffoldState=state,
                                    navController = navController,
                                    vm = vm)
                            }
                            composable(Routes.OneScreenByIdScreen.route) {
                                OneResourcesByIdScreen(
                                    scaffoldState=state ,
                                    navController = navController, vm = vm)
                            }
                        }
                    }
                }
            }

            ScreenLoadingDialog(vm = vm)
        }
    }
}
