package com.irshadillias.rakuten.assessment.feature.gitrepolist.domain

import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.enum.AlbumDomainImageSize

object DomainFixtures {

    internal fun getAlbum(
        name: String = "albumName",
        artist: String = "artistName",
        images: List<AlbumImage> = listOf(getAlbumImage()),
        wiki: AlbumWiki? = getAlbumWikiDomainModel(),
        mbId: String? = "mbId"
    ): Album = Album(name, artist, images, wiki, mbId)

    internal fun getAlbumImage(
        url: String = "url_${AlbumDomainImageSize.EXTRA_LARGE}",
        size: AlbumDomainImageSize = AlbumDomainImageSize.EXTRA_LARGE
    ) = AlbumImage(url, size)

    private fun getAlbumWikiDomainModel(
        published: String = "published",
        summary: String = "summary"
    ) = AlbumWiki(published, summary)
}
