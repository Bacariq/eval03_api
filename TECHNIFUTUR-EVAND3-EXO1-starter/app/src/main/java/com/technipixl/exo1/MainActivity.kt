package com.technipixl.exo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.technipixl.exo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        InitNav()
    }

    override fun onSupportNavigateUp() : Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return  navController.navigateUp()
    }

    private fun InitNav(){
        val navController = findNavController(R.id.fragmentContainerView)
        val toolbar = binding.myToolBar
        setSupportActionBar(toolbar)

        NavigationUI.setupActionBarWithNavController(this, navController)
        navController.addOnDestinationChangedListener{controller, destination, argumetn ->
            when(destination.id){
                R.id.charactersFragment -> toolbar.title = "Liste des heros"
                R.id.comicsDetailFragment -> toolbar.title = "Detail du comic"
                R.id.comicsFragment -> toolbar.title = "Liste des comics"
                else -> toolbar.title = null
            }
        }
    }
}