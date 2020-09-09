package com.jay.chatwithinternet.di.module

import com.jay.chatwithinternet.repository.TwitterSearchRepoImp
import com.jay.chatwithinternet.repository.TwitterSearchRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(retrofit: Retrofit):TwitterSearchRepository {
        return TwitterSearchRepoImp(retrofit)
    }
}