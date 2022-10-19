package com.cartoon.boruto.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.cartoon.boruto.data.repository.Repository
import com.cartoon.boruto.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke():Flow<PagingData<Hero>>{
        return repository.getAllHeroes()
    }
}