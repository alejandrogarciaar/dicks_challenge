package com.john.dickstest.di

import com.john.core.navigation.DefaultActionDispatcher
import com.john.dickstest.actions.DefaultActionDispatcherImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface ActionsModule {

    @Binds
    fun bindsDefaultActionDispatcher(
        dispatcher: DefaultActionDispatcherImpl
    ): DefaultActionDispatcher
}