package com.example.flicker_room.API

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClinent {
private var retrofit:Retrofit?=null
    fun GetClient():Retrofit?{
        val interceptor =HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        retrofit=Retrofit.Builder()
            .baseUrl("https://api.flickr.com/services/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}
