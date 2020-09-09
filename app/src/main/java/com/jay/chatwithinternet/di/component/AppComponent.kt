package com.jay.chatwithinternet.di.component

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.jay.chatwithinternet.MyApplication
import com.jay.chatwithinternet.di.module.FragmentModule
import com.jay.chatwithinternet.di.module.NetworkModule
import com.jay.chatwithinternet.di.module.RepositoryModule
import com.jay.sharegujjuness.di.ViewModelModule


import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, NetworkModule::class,FragmentModule::class,RepositoryModule::class,ViewModelModule::class]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }
}