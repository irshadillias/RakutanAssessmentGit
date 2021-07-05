package com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.repolist

import androidx.lifecycle.viewModelScope
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model.RepositoryData
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.model.RepositoryList
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.usecase.RepoListUseCase
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.navigation.NavManager
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.viewmodel.BaseAction
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.viewmodel.BaseViewModel
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.viewmodel.BaseViewState
import kotlinx.coroutines.launch

internal class RepoListViewModel(
    private val navManager: NavManager,
    private val getRepolistUsecase: RepoListUseCase
) : BaseViewModel<RepoListViewModel.ViewState, RepoListViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getAlbumList()
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.RepoLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            repoList = viewAction.repo.repoList,
            isNext = !viewAction.repo.next?.isEmpty()
        )
        is Action.RepoListLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            repoList = listOf()
        )
    }

    private fun getAlbumList() {
        viewModelScope.launch {
            getRepolistUsecase.execute().also { result ->
                val action = when (result) {
                    is RepoListUseCase.Result.Success ->
                        if (result.data == null) {
                            Action.RepoListLoadingFailure
                        } else {
                            Action.RepoLoadingSuccess(result.data)
                        }

                    is RepoListUseCase.Result.Error ->
                        Action.RepoListLoadingFailure
                }
                sendAction(action)
            }
        }
    }
    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val isNext : Boolean = false,
        val repoList: List<RepositoryList> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class RepoLoadingSuccess(val repo: RepositoryData) : Action()
        object RepoListLoadingFailure : Action()
    }
}
