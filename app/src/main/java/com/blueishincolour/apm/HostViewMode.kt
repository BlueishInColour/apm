package com.blueishincolour.apm

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blueishincolour.apm.model.ResModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


open class HostViewModel :ViewModel() {

    //uistate
    val uiState = mutableStateOf(
        ApmUiState()
    )

    val isScreenLoading:Boolean
        get() = uiState.value.isScreenLoading

    fun changeIsScreenLoading(value:Boolean = !isScreenLoading){
        uiState.value = uiState.value.copy(isScreenLoading = value)
    }



    fun  launchCatch(
        context: Context, block:suspend CoroutineScope.() -> Unit
    ){
        changeIsScreenLoading(true)

        viewModelScope.launch(
            context = CoroutineExceptionHandler{
                    _,throwable ->
                Toast.makeText(context,"error:  " + throwable.message, Toast.LENGTH_SHORT).show()

                Log.w(ContentValues.TAG,throwable.toString())

            },
            block=
                block
        )
        changeIsScreenLoading(false)

    }

    fun showToast(context: Context, text:String){
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_LONG
        ).show()
    }

    val backStackRes:ResModel?
        get() = uiState.value.backStackRes
    fun changeBackStackRes(res: ResModel) {
        uiState.value  = uiState.value.copy(backStackRes = res)
    }

}