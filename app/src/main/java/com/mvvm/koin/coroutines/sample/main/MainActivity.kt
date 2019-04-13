package com.mvvm.koin.coroutines.sample.main

import android.os.Bundle
import androidx.core.view.GravityCompat
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView
import com.mvvm.koin.coroutines.sample.base.BaseActivity
import com.mvvm.koin.coroutines.sample.R
import com.mvvm.koin.coroutines.sample.data.room.User
import com.mvvm.koin.coroutines.sample.databinding.ActivityMainBinding
import com.mvvm.koin.coroutines.sample.livedata.Event
import com.mvvm.koin.coroutines.sample.livedata.Status
import com.mvvm.koin.coroutines.sample.photos.PhotosFragment
import com.mvvm.koin.coroutines.sample.places.PlacesFragment
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.koin.viewModel

class MainActivity : BaseActivity< ActivityMainBinding>(), NavigationView.OnNavigationItemSelectedListener {

    val viewModel by currentScope.viewModel<MainViewModel>(this)

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getVariablesToBind(): Map<Int, Any> = emptyMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle(R.string.app_name)
        replaceFragment(PlacesFragment.newInstance(), R.id.main_content_view, PlacesFragment.TAG)
    }

    override fun initView() {
        super.initView()
        initNavMenu()
        viewModel.getUser()
    }

    private fun initNavMenu() {
        val toggle = ActionBarDrawerToggle(this, dataBinding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        dataBinding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        dataBinding.navView.setNavigationItemSelectedListener(this)
        dataBinding.navView.menu.getItem(0).isChecked = true
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.user.observe(this, userEventObserver)
    }

    private val userEventObserver = Observer<Event<User>> { onUserResponse(it) }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onUserResponse(response: Event<User>) {
        when (response.status) {
            Status.SUCCESS -> setUser(response.peekData())
            else -> showAlert(R.string.error_user)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun setUser(user: User?) {
        dataBinding.user = user
    }


    override fun onBackPressed() {
        if (dataBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            dataBinding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        onMenuItemClicked(item.itemId)
        dataBinding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onMenuItemClicked(menuItem: Int) {
        when (menuItem) {
            R.id.nav_places -> replaceFragment(PlacesFragment.newInstance(), R.id.main_content_view, PlacesFragment.TAG)
            R.id.nav_photos -> replaceFragment(PhotosFragment.newInstance(), R.id.main_content_view, PhotosFragment.TAG)
            R.id.nav_logout -> logout()
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun logout() {
        viewModel.logout()
        finishAffinity()
    }
}
