package com.assignment.githubrepolist.data.repository

import com.assignment.githubrepolist.data.api.GitApi
import com.assignment.githubrepolist.data.db.RepoDAO
import com.assignment.githubrepolist.data.model.repodetail.response.RepoDetail
import com.assignment.githubrepolist.domain.GitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitRepositoryImpl @Inject constructor(
    private val gitApi: GitApi,
    private val repoDAO: RepoDAO
): GitRepository {

    override suspend fun getRepositoryDetail(username: String, reponame: String) =
        gitApi.getUserRepos(username, reponame)

    override suspend fun saveRepositoryToLocal(repoDetail: RepoDetail) =
        repoDAO.insert(repoDetail)

    override fun getSavedRepoList(): Flow<List<RepoDetail>> {
        return repoDAO.getAllReposFromLocalDB()
    }


}