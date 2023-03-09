package com.assignment.githubrepolist.presentation.di

import android.app.Application
import androidx.room.Room
import com.assignment.githubrepolist.data.db.RepoDAO
import com.assignment.githubrepolist.data.db.RepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRepoDatabase(app: Application): RepoDatabase {
        return Room.databaseBuilder(app, RepoDatabase::class.java, "repos_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRepoDao(repoDatabase: RepoDatabase): RepoDAO {
        return repoDatabase.getRepoDAO()
    }


}