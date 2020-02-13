package io.projekt401.myapplication.api

import io.projekt401.myapplication.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("placeholder/user/{userId}")
    suspend fun getUser(
        @Path("userId") UserId: String
    ): User
}