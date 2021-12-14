package com.app.randomuser.data.repository

import com.app.randomuser.data.remote.RandomUserApi
import com.app.randomuser.data.remote.dto.ResponseRandomData
import com.app.randomuser.domain.repository.UserRepository

class FootballerRepositoryImpl(private val api: RandomUserApi) : UserRepository {

    override suspend fun getRandomUsers(): ResponseRandomData {
        return api.getRandomUsers()
    }
}