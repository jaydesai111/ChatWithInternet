package com.jay.chatwithinternet

import android.app.Application
import com.jay.chatwithinternet.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MyApplication: DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .create(this)
            .build()
    }
}