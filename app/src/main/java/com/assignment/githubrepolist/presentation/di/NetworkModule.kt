package com.assignment.githubrepolist.presentation.di

import android.app.Application
import androidx.room.Room
import com.assignment.githubrepolist.data.api.GitApi
import com.assignment.githubrepolist.data.db.RepoDAO
import com.assignment.githubrepolist.data.db.RepoDatabase
import com.assignment.githubrepolist.data.repository.GitRepositoryImpl
import com.assignment.githubrepolist.data.utils.Constants
import com.assignment.githubrepolist.data.utils.network.NetworkConnectionInterceptor
import com.assignment.githubrepolist.domain.GitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Singleton
    @Provides
    fun providesGitApi(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): GitApi {
        return retrofitBuilder.client(okHttpClient).build().create(GitApi::class.java)
    }

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

    @Singleton
    @Provides
    fun provideGitRepository(gitRepositoryImpl: GitRepositoryImpl): GitRepository {
        return gitRepositoryImpl
    }



}