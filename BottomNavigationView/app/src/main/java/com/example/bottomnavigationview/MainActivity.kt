package com.example.bottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottomnavigationview.fragments.FavoriteFragment
import com.example.bottomnavigationview.fragments.HomeFragment
import com.example.bottomnavigationview.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.nav_view)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, HomeFragment())
            .commit()

        bottomNavigationView.setOnItemSelectedListener {
            var fragment: Fragment? = null
            when(it.itemId) {
                R.id.nav_home -> {
                    fragment = HomeFragment()
                }

                R.id.nav_favorite -> {
                    fragment = FavoriteFragment()
                }

                R.id.nav_search -> {
                    fragment = SearchFragment()
                }
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment!!)
                .commit()
            return@setOnItemSelectedListener true
        }
    }
}