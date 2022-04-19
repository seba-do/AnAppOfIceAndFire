package com.ttdrp.gameofthrones.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ttdrp.gameofthrones.data.houses.HouseResponse
import com.ttdrp.gameofthrones.data.lord.LordResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

private const val BASE_URL = "https://www.anapioficeandfire.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val loggingInterceptor = HttpLoggingInterceptor().apply {
    setLevel(HttpLoggingInterceptor.Level.BODY)
}

private val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

interface ApiService {
    @GET("houses")
    suspend fun getHouses(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 30
    ): List<HouseResponse>

    @GET("characters/{id}")
    suspend fun getLord(@Path("id") id: String): LordResponse?

    @GET("houses/{id}")
    suspend fun getHouse(@Path("id") id: String): HouseResponse?
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): ApiService = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(client)
        .build()
        .create(ApiService::class.java)
}