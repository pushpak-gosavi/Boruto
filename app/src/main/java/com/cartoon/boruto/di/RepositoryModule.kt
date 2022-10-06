package com.cartoon.boruto.di

import android.content.Context
import com.cartoon.boruto.data.repository.DataStoreOperationsImpl
import com.cartoon.boruto.data.repository.Repository
import com.cartoon.boruto.domain.use_cases.UseCases
import com.cartoon.boruto.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.cartoon.boruto.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.cartoon.boruto.repository.DataStoreOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDatastoreOperations(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases (repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository)
        )
    }
}