package com.irshadillias.rakuten.assessment.feature.gitrepolist.data.network.model

import org.junit.jupiter.api.Test

class AlbumWikiNetworkDataModelTest {

    @Test
    fun `maps to AlbumWikiDomainModel`() {
        // given
        val published = "published"
        val summary = "summary"
        val cut = DataFixtures.getAlbumWikiDataModel(published, summary)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo AlbumWiki(published, summary)
    }
}
