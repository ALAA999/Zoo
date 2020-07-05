package com.s.ajrami.zoo.ui


import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.s.ajrami.zoo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_item.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = this.findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_animal,
            R.id.navigation_fish,
            R.id.navigation_bird

        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        fab.setOnClickListener {
            var i = Intent(this, Add::class.java)
            startActivityForResult(i, 33)
        }
    }
    override fun onStart() {
        super.onStart()

        findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_animal, Bundle.EMPTY, NavOptions.Builder()
            .setPopUpTo(R.id.navigation_animal, true)
            .build()
        )

    }
}
