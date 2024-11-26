package com.john.core.dispatchers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(ViewModelComponent::class)
internal object DispatchersModule {
    @Provides
    fun providesCoroutineContext(): CoroutineContext = Dispatchers.IO
}