package com.yonasoft.handballcourtmanager

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.yonasoft.handballcourtmanager.databinding.ActivityMainBinding

//Change colors for results, matches, players, and list items.

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //Sets up navigation
        setupNavigation()

    }
    //Sets up navigation
    @OptIn(NavigationUiSaveStateControl::class)
    private fun setupNavigation() {
        //Action bar
        setSupportActionBar(binding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration.Builder(R.id.rosterFragment,R.id.matchesFragment,R.id.resultsFragment).build()
        //Navigation for tool bar
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
        //Navigation for bottom nav bar
        NavigationUI.setupWithNavController(binding.bottomNav, navController, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tool_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  item.onNavDestinationSelected(findNavController(binding.navHostFragment.id))||super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}




