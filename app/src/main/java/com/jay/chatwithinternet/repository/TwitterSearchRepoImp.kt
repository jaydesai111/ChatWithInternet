package com.jay.chatwithinternet.repository

import android.util.Log
import com.jay.chatwithinternet.data.*
import com.jay.chatwithinternet.utils.BEARER_TOKEN_CREDENTIALS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

class TwitterSearchRepoImp @Inject constructor(val retrofit: Retrofit): TwitterSearchRepository {

    override suspend fun getSearchData(query: String): data = withContext(Dispatchers.IO) {
        Log.i("TwitterSearchRepoImp","this is value of "+retrofit)
        retrofit.create(Service::class.java).getSearchData(BEARER_TOKEN_CREDENTIALS,query)
    }
}