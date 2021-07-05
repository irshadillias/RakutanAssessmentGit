package com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.albumlist

import com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.repolist.RepoListViewModel
import com.irshadillias.rakuten.assessment.library_test_utils.CoroutinesTestExtension
import com.irshadillias.rakuten.assessment.library_test_utils.InstantTaskExecutorExtension
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.navigation.NavManager
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class RepoListViewModelTest {

    @ExperimentalCoroutinesApi
    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @JvmField
    @RegisterExtension
    var instantTaskExecutorExtension = InstantTaskExecutorExtension()

    @MockK
    internal lateinit var mockGetAlbumListUseCase: GetAlbumListUseCase

    @MockK(relaxed = true)
    internal lateinit var mockNavManager: NavManager

    private lateinit var cut: RepoListViewModel

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        cut = RepoListViewModel(
            mockNavManager,
            mockGetAlbumListUseCase
        )
    }

    @Test
    fun `execute getAlbumUseCase`() {
        // when
        cut.loadData()

        // then
        coVerify { mockGetAlbumListUseCase.execute() }
    }

    @Test
    fun `navigate to album details`() {
        // given
        val artistName = "Michael Jackson"
        val albumName = "Thriller"
        val mbId = "mbId"

        val navDirections = AlbumListFragmentDirections.actionAlbumListToAlbumDetail(
            artistName,
            albumName,
            mbId
        )

        // when
        cut.navigateToAlbumDetails(artistName, albumName, mbId)

        // then
        coVerify { mockNavManager.navigate(navDirections) }
    }

    @Test
    fun `verify state when GetAlbumListUseCase returns empty list`() {
        // given
        coEvery { mockGetAlbumListUseCase.execute() } returns GetAlbumListUseCase.Result.Success(emptyList())

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldBeEqualTo  AlbumDetailViewModel.ViewState(
            isLoading = false,
            isError = true,
        )
    }

    @Test
    fun `verify state when GetAlbumListUseCase returns non-empty list`() {
        // given
        val album = Album("albumName", "artistName", listOf())
        val albums = listOf(album)
        coEvery { mockGetAlbumListUseCase.execute() } returns GetAlbumListUseCase.Result.Success(albums)

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldBeEqualTo AlbumDetailViewModel.ViewState(
            isLoading = false,
            isError = false,
        )
    }
}
