import kotlin.reflect.full.memberProperties
@Suppress("unused")
object  ModuleDependency {
    // All consts are accessed via reflection
    const val APP = ":app"
    const val FEATURE_ALBUM = ":feature_gitrepolist"
    //const val FEATURE_PROFILE = ":feature_profile"
    //const val FEATURE_FAVOURITE = ":feature_favourite"
    const val LIBRARY_TEST_UTILS = ":library_test_utils"
    fun getAllModules() = ModuleDependency::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()

    fun getFeatureModules(): Set<String> {
        val featurePrefix = ":feature_"

        return getAllModules()
            .filter { it.startsWith(featurePrefix) }
            .toSet()
    }
}
