package com.jay.chatwithinternet.data

import retrofit2.Call
import retrofit2.http.*

interface Service {

    @GET("2/tweets/search/recent")
    suspend fun getSearchData(@Header("Authorization") authorization: String,
    @Query("query") query: String): data

}