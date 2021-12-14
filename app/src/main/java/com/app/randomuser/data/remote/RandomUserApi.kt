package com.app.randomuser.data.remote

import retrofit2.http.GET
import com.app.randomuser.data.remote.dto.ResponseRandomData

interface RandomUserApi {

    @GET("/api/?inc=gender,email,name,phone,picture&results=10")
    suspend fun getRandomUsers(): ResponseRandomData
}