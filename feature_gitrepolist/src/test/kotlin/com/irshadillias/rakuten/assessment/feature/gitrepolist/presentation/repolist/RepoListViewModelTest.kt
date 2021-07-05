package com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.repolist

import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.usecase.RepoListUseCase
import com.irshadillias.rakuten.assessment.library_test_utils.CoroutinesTestExtension
import com.irshadillias.rakuten.assessment.library_test_utils.InstantTaskExecutorExtension
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.navigation.NavManager
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun `execute getAlbumUseCase`() {
        // when
        cut.loadData()

        // then
        coVerify { mockGetAlbumListUseCase.execute() }
    }


}
