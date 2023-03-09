package com.assignment.githubrepolist.presentation.di

import com.assignment.githubrepolist.data.repository.GitRepositoryImpl
import com.assignment.githubrepolist.domain.GitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGitRepository(gitRepositoryImpl: GitRepositoryImpl): GitRepository {
        return gitRepositoryImpl
    }

}