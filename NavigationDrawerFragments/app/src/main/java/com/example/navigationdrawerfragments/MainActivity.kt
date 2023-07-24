package com.example.navigationdrawerfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.navigationdrawerfragments.fragments.ChatFragment
import com.example.navigationdrawerfragments.fragments.MessageFragment
import com.example.navigationdrawerfragments.fragments.ProfileFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this@MainActivity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MessageFragment())
            .commit()
        navigationView.setCheckedItem(R.id.nav_message)

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_message -> {
                    Toast.makeText(this@MainActivity, "Message cliked", Toast.LENGTH_LONG).show()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, MessageFragment())
                        .commit()
                }

                R.id.nav_chat -> {
                    Toast.makeText(this@MainActivity, "Chat cliked", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ChatFragment())
                        .commit()
                }

                R.id.nav_profile -> {
                    Toast.makeText(this@MainActivity, "Profile cliked", Toast.LENGTH_LONG).show()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()
                }

                R.id.nav_send -> {
                    Toast.makeText(this@MainActivity, "Send cliked", Toast.LENGTH_LONG).show()
                }

                R.id.nav_share -> {
                    Toast.makeText(this@MainActivity, "Share cliked", Toast.LENGTH_LONG).show()
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
}