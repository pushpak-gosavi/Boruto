package com.cartoon.boruto.data.repository

import com.cartoon.boruto.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private  val datastore: DataStoreOperations
)  {
    suspend fun saveOnBoardingState(completed:Boolean){
        datastore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState():Flow<Boolean>{
        return datastore.readOnBoardingState()
    }
}