package com.example.ask_nitt

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetroInstance {
    val api1: PostRegister by lazy{
        Retrofit.Builder()
            .baseUrl("https://ask-nitt-doubt.onrender.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostRegister::class.java)
    }
}
