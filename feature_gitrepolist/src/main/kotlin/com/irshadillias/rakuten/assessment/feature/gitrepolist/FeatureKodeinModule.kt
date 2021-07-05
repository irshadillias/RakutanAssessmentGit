package com.irshadillias.rakuten.assessment.feature.gitrepolist

import com.irshadillias.rakuten.assessment.feature.gitrepolist.data.dataModule
import com.irshadillias.rakuten.assessment.feature.gitrepolist.domain.domainModule
import com.irshadillias.rakuten.assessment.feature.gitrepolist.presentation.presentationModule
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.app.feature.KodeinModuleProvider
import org.kodein.di.Kodein

internal const val MODULE_NAME = "gitrepolist"

object FeatureKodeinModule : KodeinModuleProvider {

    override val kodeinModule = Kodein.Module("${MODULE_NAME}Module") {
        import(presentationModule)
        import(domainModule)
        import(dataModule)
    }
}
