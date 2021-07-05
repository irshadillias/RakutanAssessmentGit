package com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation

import androidx.fragment.app.Fragment
import coil.ImageLoader
import com.irshadillias.rakuten.assessment.feature.gitrepolist.MODULE_NAME
import com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.repolist.RepoListViewModel
import com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.repolist.recyclerview.RepoAdapter
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.di.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    // AlbumList
    bind<RepoListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { RepoListViewModel(instance(), instance()) }
    }

    bind() from singleton { RepoAdapter() }

    bind() from singleton { ImageLoader(instance()) }

}
