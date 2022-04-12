package tj.saburjonsafarov.newsfromapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.databinding.ActivityMainBinding
import tj.saburjonsafarov.newsfromapi.checks.Checking
import tj.saburjonsafarov.newsfromapi.repository.DBHelper

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!Checking().checkForInternet(this)) {
            AlertDialog.Builder(this)
                .setTitle("no internet")
                .setPositiveButton("retry") { _, _ ->
                    finish()
                    startActivity(Intent(this, MainActivity()::class.java))
                }
                .create()
                .show()
        }

        binding.bottomNavigationView.setOnItemSelectedListener(this@MainActivity)
        openFragmentHelper(HomeFragment())
        binding.toolbar.title = "Home"

        binding.searchView.isSubmitButtonEnabled = true

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, SavedsFragment.newInstance(query.toString()))
                    .commit()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeIcon -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, HomeFragment())
                    .commit()
                binding.toolbar.title = "Home"
                binding.searchView.isVisible = true
                return true
            }
            R.id.historyIcon -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, SavedsFragment.newInstance(DBHelper.History_TABLE))
                    .commit()
                binding.toolbar.title = "History"
                binding.searchView.isVisible = false
                return true
            }

            R.id.favoriteIcon -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, SavedsFragment.newInstance(DBHelper.FAVORITES_TABLE))
                    .commit()
                binding.toolbar.title = "Favorites"
                binding.searchView.isVisible = false
                return true
            }

            R.id.settingsIcon -> {
                openFragmentHelper(SettingsFragment())
                binding.toolbar.title = "Settings"
                binding.searchView.isVisible = false
                return true
            }

        }
        return false
    }

    private fun openFragmentHelper(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

}