package com.blueishincolour.apm.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.blueishincolour.apm.ApmViewModel
import com.blueishincolour.apm.Routes
import com.blueishincolour.apm.model.ResModel


@Composable
fun ResListTile(
    vm: ApmViewModel = hiltViewModel(),
    navController : NavHostController,
    res: ResModel
) {
    Column(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .clickable {
                vm.changeResModel(res)
                navController.navigate(Routes.OneScreenByIdScreen.route)
            }
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            SmallResImage(uri = res.resPicture,vm= vm)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = res.resId)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = res.resTitle,
                maxLines= 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
