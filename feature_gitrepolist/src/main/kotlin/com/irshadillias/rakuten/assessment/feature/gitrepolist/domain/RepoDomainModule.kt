package com.irshadillias.rakuten.assessment.feature.gitrepolist.domain

import com.irshadillias.rakuten.assessment.feature.gitrepolist.MODULE_NAME
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.usecase.RepoListUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {
    bind() from singleton { RepoListUseCase(instance()) }
}
