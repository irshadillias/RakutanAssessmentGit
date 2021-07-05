package com.irshadillias.rakuten.assessment.feature.gitrepolist.data

import com.irshadillias.rakuten.assessment.feature.gitrepolist.data.network.response.gitRepoToViewData
import com.irshadillias.rakuten.assessment.feature.gitrepolist.data.network.service.RepoListRetrofitService
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model.RepositoryData
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.repository.RepoListRepository
internal class RepoListRepositoryImpl(
    private val repoRetrofitService: RepoListRetrofitService,
) : RepoListRepository {

    override suspend fun fetchRepos(): RepositoryData {
        return repoRetrofitService.fetchRepos().gitRepoToViewData()
    }

}
