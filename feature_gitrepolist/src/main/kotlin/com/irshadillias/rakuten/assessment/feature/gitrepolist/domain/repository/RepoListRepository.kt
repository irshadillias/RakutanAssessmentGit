package com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.repository

import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model.RepositoryData

internal interface RepoListRepository {
    suspend fun fetchRepos() : RepositoryData
}
