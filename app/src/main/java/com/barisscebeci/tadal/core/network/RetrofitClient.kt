package com.barisscebeci.tadal.core.network

import com.barisscebeci.tadal.data.remote.FoodApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object RetrofitClient {
    private const val BASE_URL = "http://kasimadalan.pe.hu/"

    private val gson: Gson = GsonBuilder()
        .serializeNulls()   //sepetteki son yemek silindiğinde null değer döndürmesi için
        .setLenient()
        .create()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val apiService: FoodApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(FoodApiService::class.java)
    }
}