package com.irshadillias.rakuten.assessment.feature.gitrepolist.data

import com.irshadillias.rakuten.assessment.feature.gitrepolist.data.network.service.RepoListRetrofitService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.UnknownHostException

class RepoListRepositoryImplTest {

    @MockK
    internal lateinit var mockService: RepoListRetrofitService

    private lateinit var cut: RepoListRepositoryImpl

    private val artistName = "Michael Jackson"
    private val albumName = "Thriller"

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        cut = RepoListRepositoryImpl(mockService)
    }

    @Test
    fun `searchAlbum fetches AlbumInfo and maps to Model`() {
        // given
        val phrase = "phrase"
        /*coEvery { mockService.fetchRepos() } returns GitReposponse(
            listOf(DataFixtures.getAlbum()))
        )*/

        // when
        val result = runBlocking { cut.fetchRepos() }

        // then
        //result shouldBeEqualTo listOf(DataFixtures.getAlbum().toDomainModel())
    }

    @Test
    fun `searchAlbum return data from database if offline`() {
        // given
        val phrase = "phrase"
        //val albumsJson = DataFixtures.getAlbums()

        coEvery { mockService.fetchRepos() } throws UnknownHostException()

        // when
        val result = runBlocking { cut.fetchRepos() }

        // then
        result shouldBeEqualTo null
    }

}
