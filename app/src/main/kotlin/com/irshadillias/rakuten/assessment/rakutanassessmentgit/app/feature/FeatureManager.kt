package com.irshadillias.rakuten.assessment.rakutanassessmentgit.app.feature

import com.irshadillias.rakuten.assessment.rakutanassessmentgit.BuildConfig

@Suppress("detekt.UnsafeCast")
object FeatureManager {

    private const val featurePackagePrefix = "com.irshadillias.rakuten.assessment.feature"

    val kodeinModules = BuildConfig.FEATURE_MODULE_NAMES
        .map { "$featurePackagePrefix.$it.FeatureKodeinModule" }
        .map {
            try {
                Class.forName(it).kotlin.objectInstance as KodeinModuleProvider
            } catch (e: ClassNotFoundException) {
                throw ClassNotFoundException("Kodein module class not found $it")
            }
        }
        .map { it.kodeinModule }
}
