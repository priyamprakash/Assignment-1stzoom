package com.assignment.githubrepolist.db

import androidx.room.*
import com.assignment.githubrepolist.model.repodetail.response.RepoDetailResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repoDetailResponse: RepoDetailResponse)

    @Query("SELECT * FROM repos")
    fun getAllReposFromLocalDB(): Flow<List<RepoDetailResponse>>


}