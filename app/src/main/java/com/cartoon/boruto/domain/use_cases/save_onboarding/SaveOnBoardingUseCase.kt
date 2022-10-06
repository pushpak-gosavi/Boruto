package com.cartoon.boruto.domain.use_cases.save_onboarding

import com.cartoon.boruto.data.repository.Repository

class SaveOnBoardingUseCase (
    private val repository: Repository
        ) {
    suspend operator fun invoke (completed:Boolean){
        repository.saveOnBoardingState(completed = completed)
    }
}