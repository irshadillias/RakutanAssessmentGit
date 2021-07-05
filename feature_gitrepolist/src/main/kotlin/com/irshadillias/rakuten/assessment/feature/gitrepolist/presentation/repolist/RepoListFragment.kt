package com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.repolist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.irshadillias.rakuten.assessment.feature.gitrepolist.R
import com.irshadillias.rakuten.assessment.feature.gitrepolist.databinding.FragmentAlbumListBinding
import com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.repolist.recyclerview.GridAutofitLayoutManager
import com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.repolist.recyclerview.RepoAdapter
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.delegate.viewBinding
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.extension.observe
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.extension.visible
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.fragment.InjectionFragment
import org.kodein.di.generic.instance


class RepoListFragment : InjectionFragment(R.layout.fragment_album_list) {

    private val binding: FragmentAlbumListBinding by viewBinding()

    private val viewModel: RepoListViewModel by instance()

    private val repoAdapter: RepoAdapter by instance()

    private val stateObserver = Observer<RepoListViewModel.ViewState> {
        repoAdapter.repoList = it.repoList
        binding.isnext.visible = it.isNext
        binding.progressBar.visible = it.isLoading
        binding.errorAnimation.visible = it.isError
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = requireContext()

        repoAdapter.setOnDebouncedClickListener {
            if(!it.website?.isEmpty()){
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.website))
                startActivity(browserIntent)
            }
        }
        binding.recyclerView.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.getDimension(R.dimen.image_size).toInt()
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth
                )
            adapter = repoAdapter
        }

        observe(viewModel.stateLiveData, stateObserver)

        viewModel.loadData()
    }
}
