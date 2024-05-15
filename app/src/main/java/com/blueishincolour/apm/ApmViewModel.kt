package com.blueishincolour.apm

import android.content.Context
import android.net.Uri
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.blueishincolour.apm.model.ResModel
import com.blueishincolour.apm.service.ApmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApmViewModel
@Inject constructor()
    : HostViewModel(){
        private val repo = ApmRepository()


    val resModel = mutableStateOf(
        ResModel()
    )

    val resId:String
        get() = resModel.value.resId
    val resPicture:String
        get() = resModel.value.resPicture
    val resPictureSuffix:String
        get() = resModel.value.resPictureSuffix

    val resTitle:String
        get() = resModel.value.resTitle
    val resStory:String
        get() = resModel.value.resStory


    fun changeResModel(value:ResModel){
        resModel.value  = value
    }
    fun changeResId(value:String){
        resModel.value = resModel.value.copy(resId = value)
    }
    fun changeResPicture(value:String){
        resModel.value = resModel.value.copy(resPicture = value)
    }
    fun changeResPictureSuffix(value:String){
        resModel.value = resModel.value.copy(resPictureSuffix = value)
    }
    fun changeResTitle(value:String){
        resModel.value = resModel.value.copy(resTitle = value)
    }
    fun changeResStory(value:String){
        resModel.value = resModel.value.copy(resStory = value)
    }


    //firestore
    fun setRes(context :Context){
        changeIsScreenLoading(true)

        launchCatch(context){

            repo.setRes(
                res = resModel.value,
                isCompleted = {
                    resModel.value = ResModel()
                    showToast(context,"add res successful")
                              changeIsScreenLoading(false)
                              },
                isFailed = {
                    showToast(context,"failed to add res")
                    changeIsScreenLoading(false)
                }
            )
        }

    }

    fun fetchAllRes(context: Context,isCompleted:(ResModel)->Unit){
        changeIsScreenLoading(true)

        launchCatch(context){

            repo.fetchAllRes(
                isCompleted = {
                    changeIsScreenLoading(false)
                    isCompleted(it)
                    showToast(context,"successfully fetched res")

                },
                isFailed = {
                    changeIsScreenLoading(false)
                    showToast(context,"failed to fetch res")
                }
            )
        }
    }
    fun setImage(context: Context,uri: Uri){
        changeIsScreenLoading(true)
        launchCatch(context){
            repo.setImage(
                uri = uri,
                pictureSuffix = resPictureSuffix,
                isCompleted = {
                    changeIsScreenLoading(false)
                    changeResPicture(it.toString())
                    showToast(context,"image upload successful")},
                isFailed = {
                    changeIsScreenLoading(false)
                    showToast(context,"failed to set image")}
            )
        }
    }


    //bottomsheet
    @OptIn(ExperimentalMaterial3Api::class)
    fun openBottomSheet(context:Context,
                        scaffoldState: BottomSheetScaffoldState,
    ){
       launchCatch(context){
           scaffoldState.bottomSheetState.expand()
       }
    }

@OptIn(ExperimentalMaterial3Api::class)
fun closeBottomSheet(context:Context,
                    scaffoldState: BottomSheetScaffoldState,
){
    launchCatch(context){
        scaffoldState.bottomSheetState.hide()
    }
}

    fun deleteRes(context: Context, resId: String,navController  :NavHostController) {
launchCatch(context){
    repo.deleteRes(
        resId =resId,
        isCompleted = {showToast(context,"delete successful")
                      navController.popBackStack()},
        isFailed = {showToast(context,"failed to delete")}
    )
}
    }
}