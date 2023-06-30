package com.ceydaceyhan.androidassignment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {

    fun getClient(): UsersInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(UsersInterface::class.java)
    }
}

