package com.irshadillias.rakuten.assessment.rakutanassessmentgit.app.presentation

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.R
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.delegate.viewBinding
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.extension.navigateSafe
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.activity.BaseActivity
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.base.presentation.navigation.NavManager
import com.irshadillias.rakuten.assessment.rakutanassessmentgit.databinding.ActivityNavHostBinding
import org.kodein.di.generic.instance

class NavHostActivity : BaseActivity() {

    private val binding: ActivityNavHostBinding by viewBinding()

    private val navController get() = findNavController(this, R.id.navHostFragment)

    private val navManager: NavManager by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initBottomNavigation()
        initNavManager()
    }

    private fun initBottomNavigation() {
        binding.bottomNav.setupWithNavController(navController)

        // Disables reselection of bottom menu item, so fragments are not recreated when clicking on the same menu item
        binding.bottomNav.setOnNavigationItemReselectedListener { }
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)

            currentFragment?.navigateSafe(it)
        }
    }
}
