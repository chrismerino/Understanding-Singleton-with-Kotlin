package me.projekt401.singletonwithkotlin.api

import me.projekt401.singletonwithkotlin.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("placeholder/user/{userId}")
    // Using Coroutines to return this from the network
    suspend fun getUser(
        @Path("userId") userId: String
    ): User
}