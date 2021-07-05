package com.irshadillias.rakuten.assessment.feature.gitrepolist.data.network.service

import com.irshadillias.rakuten.assessment.feature.gitrepolist.data.network.response.GitReposponse
import retrofit2.http.GET

internal interface RepoListRetrofitService {

    @GET("repositories")
    suspend fun fetchRepos(): GitReposponse
}
