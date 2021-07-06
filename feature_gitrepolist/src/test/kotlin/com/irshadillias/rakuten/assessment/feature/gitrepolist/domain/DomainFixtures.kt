package com.irshadillias.rakuten.assessment.feature.gitrepolist.domain

import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.enum.AlbumDomainImageSize
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model.RepositoryData
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model.RepositoryList

object DomainFixtures {
    internal fun getRepoData(
        next: String = "https://testinng.com",
        repoList: List<RepositoryList> = listOf(getRepoList()),
    ): RepositoryData = RepositoryData(repoList, next)

    internal fun getRepoList(
        imageUrl: String = "testing",
        name: String = "testing",
        type: String = "testing",
        timestamp: String = "testing",
        size: String = "testing",
        description: String = "testing",
        uuid: String = "testing",
        website : String = "testing"
    ) = RepositoryList(imageUrl, name, type, timestamp, size, description, uuid,website)

}
