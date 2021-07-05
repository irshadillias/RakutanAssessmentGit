package com.irshadillias.rakuten.assessment.feature.gitrepolist.data

import com.irshadillias.rakuten.assessment.feature.gitrepolist.MODULE_NAME
import com.irshadillias.rakuten.assessment.feature.gitrepolist.data.network.service.RepoListRetrofitService
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.repository.RepoListRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("${MODULE_NAME}DataModule") {

    bind<RepoListRepository>() with singleton { RepoListRepositoryImpl(instance()) }

    bind() from singleton { instance<Retrofit>().create(RepoListRetrofitService::class.java) }
}
