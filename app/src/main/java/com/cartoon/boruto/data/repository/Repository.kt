package com.cartoon.boruto.data.repository

import androidx.paging.PagingData
import com.cartoon.boruto.domain.model.Hero
import com.cartoon.boruto.domain.repository.DataStoreOperations
import com.cartoon.boruto.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private  val datastore: DataStoreOperations
)  {

    fun getAllHeroes():Flow<PagingData<Hero>>{
        return remote.getAllHeroes()
    }
    suspend fun saveOnBoardingState(completed:Boolean){
        datastore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState():Flow<Boolean>{
        return datastore.readOnBoardingState()
    }
}