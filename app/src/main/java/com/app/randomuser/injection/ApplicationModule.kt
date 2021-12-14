package com.app.randomuser.injection

import com.app.randomuser.data.remote.RandomUserApi
import com.app.randomuser.data.repository.FootballerRepositoryImpl
import com.app.randomuser.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideUserApi(): RandomUserApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://randomuser.me")
        .build()
        .create(RandomUserApi::class.java)

    @Provides
    fun provideUserRepository(api: RandomUserApi): UserRepository =
        FootballerRepositoryImpl(api)
}