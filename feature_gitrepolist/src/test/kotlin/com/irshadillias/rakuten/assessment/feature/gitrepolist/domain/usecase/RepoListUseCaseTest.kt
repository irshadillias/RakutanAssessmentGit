package com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.usecase

import com.irshadillias.rakuten.assessment.feature.gitrepolist.data.RepoListRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.UnknownHostException

class RepoListUseCaseTest {

    @MockK
    internal lateinit var mockRepoListRepository: RepoListRepositoryImpl

    private lateinit var cut: RepoListUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        cut = RepoListUseCase(mockRepoListRepository)
    }

   /* @Test
    fun `return list of albums`() {
        // given
        val albums = listOf(DomainFixtures.getAlbum(), DomainFixtures.getAlbum())
        coEvery { mockRepoListRepository.fetchRepos() } returns albums

        // when
        val result = runBlocking { cut.execute() }

        // then
        result shouldBeEqualTo RepoListUseCase.Result.Success(albums)
    }

    @Test
    fun `filter albums without default image`() {
        // given
        val albumWithImage = DomainFixtures.getAlbum()
        val albumWithoutImage = DomainFixtures.getAlbum(images = listOf())
        val albums = listOf(albumWithImage, albumWithoutImage)
        coEvery { mockRepoListRepository.fetchRepos() } returns albums

        // when
        val result = runBlocking { cut.execute() }

        // then
        result shouldBeEqualTo RepoListUseCase.Result.Success(listOf(albumWithImage))
    }

    @Test
    fun `return error when repository throws an exception`() {
        // given
        val exception = UnknownHostException()
        coEvery { mockRepoListRepository.fetchRepos() } throws exception

        // when
        val result = runBlocking { cut.execute() }

        // then
        result shouldBeEqualTo RepoListUseCase.Result.Error(exception)
    }*/
}
