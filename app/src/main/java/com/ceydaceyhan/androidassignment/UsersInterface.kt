package com.ceydaceyhan.androidassignment

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersInterface {

    @GET("/api/users")
    fun getAllUsers(): Call<Users>

    @GET("/api/users/{id}")
    fun getSingleUser(
        @Path("id") id: Int 
    ): Call<DetayResponse>


}