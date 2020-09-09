package com.jay.chatwithinternet.repository

import com.jay.chatwithinternet.data.SearchResponse
import com.jay.chatwithinternet.data.data

interface TwitterSearchRepository {

    suspend fun getSearchData(query: String): data

}