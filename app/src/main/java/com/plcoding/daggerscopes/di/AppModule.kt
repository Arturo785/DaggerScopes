package com.plcoding.daggerscopes.di

import com.plcoding.daggerscopes.SessionTimer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    // if you inject this dependency into a viewModel then it will attach to the viewModel lifeCycle
    // if viewModel attach to fragment then as long as that fragment exists the viewModel will thus this also will live
    @Provides
    @ViewModelScoped
    fun provideSessionTimer(): SessionTimer {
        return SessionTimer()
    }
}