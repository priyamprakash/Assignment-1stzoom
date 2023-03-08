package com.assignment.githubrepolist.viewmodel

import android.service.autofill.UserData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.githubrepolist.model.repodetail.response.RepoDetailResponse
import com.assignment.githubrepolist.repository.GitRepository
import com.assignment.githubrepolist.utils.*
import com.assignment.githubrepolist.utils.network.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitViewModel @Inject constructor(private val gitRepository: GitRepository) : ViewModel() {

    private val _getRepoDetailUiState = MutableStateFlow(GetRepoDetailUIState())
    val addRepoUiState = _getRepoDetailUiState.asStateFlow()

    fun getRepositoryDetail(username: String , reponame: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _getRepoDetailUiState.update {
                it.copy(
                    loading = true
                )
            }
            handleApi {
                gitRepository.getRepositoryDetail(username, reponame)
            }.onSuccess { response ->

                _getRepoDetailUiState.update {
                    it.copy(
                        loading = false,
                        repoDetails = response
                    )
                }

            }.onError { _, message ->
                _getRepoDetailUiState.update {
                    it.copy(
                        loading = false,
                        message = message
                    )
                }

            }.onException { throwable ->
                if (throwable is NoConnectivityException) {
                    _getRepoDetailUiState.update {
                        it.copy(
                            loading = false,
                            message = "No internet connectivity"
                        )
                    }
                } else {
                    _getRepoDetailUiState.update {
                        it.copy(
                            loading = false,
                            message = "${throwable.message}"
                        )
                    }
                }
            }
        }
    }
}
data class GetRepoDetailUIState(
    val loading: Boolean = false,
    val message : String? = null,
    val repoDetails: RepoDetailResponse? = null
)
