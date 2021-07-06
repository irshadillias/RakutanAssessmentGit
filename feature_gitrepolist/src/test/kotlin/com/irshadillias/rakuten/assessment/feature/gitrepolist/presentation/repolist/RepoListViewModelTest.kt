package com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.repolist

import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.DomainFixtures
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model.RepositoryData
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.usecase.RepoListUseCase
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

class GetRepoListViewModelTest {

    @ExperimentalCoroutinesApi
    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @JvmField
    @RegisterExtension
    var instantTaskExecutorExtension = InstantTaskExecutorExtension()

    @MockK
    internal lateinit var mockGetAlbumListUseCase: RepoListUseCase

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
    fun `execute getRepoUseCase`() {
        // when
        cut.loadData()

        // then
        coVerify { mockGetAlbumListUseCase.execute() }
    }

    @Test
    fun `verify state empty list repolist`() {
        // given
        coEvery { mockGetAlbumListUseCase.execute() } returns RepoListUseCase.Result.Success(
            RepositoryData( emptyList(), "")
        )

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldBeEqualTo  RepoListViewModel.ViewState(
            isLoading = false,
            isError = false,
            isNext  = false,
            repoList= listOf()
        )
    }


    @Test
    fun `verify next button enabled`() {
        // given
        coEvery { mockGetAlbumListUseCase.execute() } returns RepoListUseCase.Result.Success(
            RepositoryData( emptyList(), "httpe://testing.com")
        )

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldBeEqualTo  RepoListViewModel.ViewState(
            isLoading = false,
            isError = false,
            isNext  = true,
            repoList= listOf()
        )
    }

   @Test
    fun `verify state when GetAlbumListUseCase returns non-empty list`() {
        // given
        val repolist = DomainFixtures.getRepoData()
        coEvery { mockGetAlbumListUseCase.execute() } returns RepoListUseCase.Result.Success(repolist)

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldBeEqualTo RepoListViewModel.ViewState(
            isLoading = false,
            isError = false,
            isNext = true,
            repoList = repolist.repoList
        )
    }


}
