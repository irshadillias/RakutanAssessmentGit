package com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.usecase

import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model.RepositoryData
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.repository.RepoListRepository
import java.io.IOException

internal class RepoListUseCase (
    private val repoListRepository: RepoListRepository
) {
    sealed class Result {
        data class Success(val data: RepositoryData) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(): Result {
        return try {
            Result.Success(repoListRepository.fetchRepos())
        } catch (e: IOException) {
            Result.Error(e)
        }
    }

}