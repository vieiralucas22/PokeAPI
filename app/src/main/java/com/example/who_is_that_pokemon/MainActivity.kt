package com.example.who_is_that_pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.who_is_that_pokemon.ui.theme.WhoisthatpokemonTheme
import com.example.who_is_that_pokemon.ui.view.HomeView
import com.example.who_is_that_pokemon.ui.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeViewModel = ViewModelProvider.create(this)[HomeViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            WhoisthatpokemonTheme {
                HomeView(homeViewModel)
            }
        }
    }
}
