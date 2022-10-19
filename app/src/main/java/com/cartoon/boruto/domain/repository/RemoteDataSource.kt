package com.cartoon.boruto.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.cartoon.boruto.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes() : Flow<PagingData<Hero>>
    fun searchHeroes() : Flow<PagingData<Hero>>
}