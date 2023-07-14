package com.code.suitmedia.di

import com.code.suitmedia.domain.usecase.DataInteractor
import com.code.suitmedia.domain.usecase.DataUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideNewsUseCase(dataInteractor: DataInteractor): DataUseCase

}
