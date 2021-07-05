package com.irshadillias.rakuten.assessment.rakutanassessmentgit.base

import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.navigation.NavManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

internal const val MODULE_NAME = "Base"

val baseModule = Kodein.Module("${MODULE_NAME}Module") {

    bind() from singleton { NavManager() }
}
