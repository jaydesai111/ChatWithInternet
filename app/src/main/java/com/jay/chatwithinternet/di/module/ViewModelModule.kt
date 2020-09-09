package com.jay.sharegujjuness.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eventtracker.app.di.utils.ViewModelFactory
import com.eventtracker.app.di.utils.ViewModelKey
import com.jay.chatwithinternet.ui.chatscreen.ChatViewModel


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun bindSelectPlanetsViewModel(viewModel: ChatViewModel): ViewModel




}