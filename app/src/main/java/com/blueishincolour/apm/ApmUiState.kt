package com.blueishincolour.apm

import com.blueishincolour.apm.model.ResModel

data class ApmUiState(
    val isScreenLoading:Boolean = false,
    val backStackRes: ResModel? = null
)
