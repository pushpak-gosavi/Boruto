package com.cartoon.boruto.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.cartoon.boruto.data.local.BorutoDatabase
import com.cartoon.boruto.data.remote.BorutoApi
import com.cartoon.boruto.domain.model.Hero
import com.cartoon.boruto.domain.model.HeroRemoteKeys
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi, // Fetch the data from API
    private val borutoDatabase: BorutoDatabase // Store data in Database
): RemoteMediator<Int, Hero>() {
    private val heroDao = borutoDatabase.heroDao()
    private val heroRemoteKeysDao = borutoDatabase.heroRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
       return try {
            val response = borutoApi.getAllHeroes(page = 1)
            if (response.heroes.isEmpty()) {
                if (loadType == LoadType.REFRESH) {
                    heroDao.deleteAllHeroes()
                    heroRemoteKeysDao.deleteAllRemoteKeys()
                }
                val prevPage = response.prevPage
                val nextPage = response.nextPage
                val keys = response.heroes.map { hero ->
                    HeroRemoteKeys(
                        id = hero.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                heroRemoteKeysDao.addAllRemoteKey(heroRemoteKeys = keys)
                heroDao.addHeroes(heroes = response.heroes)
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}