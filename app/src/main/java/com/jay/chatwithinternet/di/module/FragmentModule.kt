package com.jay.chatwithinternet.di.module

import com.jay.chatwithinternet.ui.chatscreen.ChatFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contribiutechatFragment(): ChatFragment
}