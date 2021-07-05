package com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model

data class RepositoryList(
    val imageUrl: String,
    val name: String,
    val type: String,
    val timestamp: String,
    val size: String,
    val description: String,
    val uuid: String,
    val website : String
)

data class RepositoryData(
    val repoList: List<RepositoryList>,
    val next : String
)


