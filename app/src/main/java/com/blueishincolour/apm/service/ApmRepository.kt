package com.blueishincolour.apm.service

import android.net.Uri
import com.blueishincolour.apm.model.ResModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import java.util.UUID

class ApmRepository (
    private val firestore: FirebaseFirestore = Firebase.firestore
){
    suspend fun fetchAllRes(
        isCompleted:(ResModel)->Unit,
        isFailed:()->Unit
    ){

        firestore
            .collection("app")
            .get()
            .addOnCompleteListener {manyDocument->
                for(document in manyDocument.result){
                    val res = document.toObject(ResModel::class.java)
                    if (res != null) {
                        isCompleted(res)
                    }
                }
            }
            .addOnFailureListener { isFailed() }
    }

    suspend fun fetchOneRes(resId:String,
                            isCompleted:(ResModel)->Unit,
                            isFailed:()->Unit
    ){

        firestore
            .collection("app")
            .document(resId)
            .get()
            .addOnCompleteListener {document->
                val res = document.result.toObject(ResModel::class.java)
                if (res != null) {
                    isCompleted(res)
                }
            }
            .addOnFailureListener {  isFailed() }
    }

    suspend fun setRes(
        res:ResModel,
        isCompleted:()->Unit,
        isFailed:()->Unit
    ){
        firestore
            .collection("app")
            .document(res.resId)
            .set(res)
            .addOnCompleteListener { isCompleted() }
            .addOnFailureListener {  isFailed() }
    }


    suspend fun deleteRes(resId:String,
                          isCompleted:()->Unit,
                          isFailed:()->Unit){
        firestore
            .collection("app")
            .document(resId)
            .delete()
            .addOnCompleteListener { isCompleted() }
            .addOnFailureListener {  isFailed() }
    }




     suspend fun setImage(
         uri: Uri,
         isCompleted: (uri: Uri?) -> Unit,
         isFailed: () -> Unit,
         pictureSuffix: String
     ) {
         val storage: FirebaseStorage = Firebase.storage
         val storageRef = storage.reference
         val imageRef = storageRef.child("images/${UUID.randomUUID()}.$pictureSuffix")
         imageRef.putFile(uri)
             .addOnSuccessListener {
                 imageRef.downloadUrl.addOnSuccessListener {
                     isCompleted(it)
                 }
             }
             .addOnFailureListener {

                 isFailed()
             }

     }

}
