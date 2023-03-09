package com.assignment.githubrepolist.repository

import com.assignment.githubrepolist.api.GitApi
import com.assignment.githubrepolist.db.RepoDAO
import com.assignment.githubrepolist.model.repodetail.response.RepoDetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitRepository @Inject constructor(
    private val gitApi: GitApi,
    private val repoDAO: RepoDAO
) {

    suspend fun getRepositoryDetail(username: String, reponame: String) =
        gitApi.getUserRepos(username, reponame)

    suspend fun saveRepositoryToLocal(repoDetailResponse: RepoDetailResponse) =
        repoDAO.insert(repoDetailResponse)

    fun getSavedRepoList(): Flow<List<RepoDetailResponse>> {
        return repoDAO.getAllReposFromLocalDB()
    }


}