package com.assignment.githubrepolist.data.db

import androidx.room.*
import com.assignment.githubrepolist.data.model.repodetail.response.RepoDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repoDetail: RepoDetail)

    @Query("SELECT * FROM repos")
    fun getAllReposFromLocalDB(): Flow<List<RepoDetail>>


}