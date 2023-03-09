package com.assignment.githubrepolist.domain

import com.assignment.githubrepolist.data.model.repodetail.response.RepoDetail
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GitRepository {

    suspend fun getRepositoryDetail(username: String, reponame: String): Response<RepoDetail>

    suspend fun saveRepositoryToLocal(repoDetail: RepoDetail)

    fun getSavedRepoList(): Flow<List<RepoDetail>>
}