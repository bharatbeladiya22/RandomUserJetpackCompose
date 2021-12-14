package com.app.randomuser.domain.repository

import com.app.randomuser.data.remote.dto.ResponseRandomData

interface UserRepository {

    suspend fun getRandomUsers(): ResponseRandomData
}