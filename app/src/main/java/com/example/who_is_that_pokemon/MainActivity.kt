package com.example.who_is_that_pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.who_is_that_pokemon.screens.common.Routes
import com.example.who_is_that_pokemon.screens.home.HomeScreen
import com.example.who_is_that_pokemon.screens.pokemondetails.PokemonDetailsScreen
import com.example.who_is_that_pokemon.screens.home.HomeViewModel
import com.example.who_is_that_pokemon.screens.pokemondetails.PokemonDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Routes.HomeView, builder = {

                composable(Routes.HomeView)
                {
                    val homeViewModel: HomeViewModel = hiltViewModel()
                    HomeScreen(
                        homeViewModel,
                        onPokemonClick = { pokemonName ->
                            navController.navigate(Routes.PokemonDetailsView + "/" + pokemonName)
                        }, onSearch = { pokemonKey ->
                            navController.navigate(Routes.PokemonDetailsView + "/" + pokemonKey)
                        })
                }

                composable(Routes.PokemonDetailsView + "/{name}")
                {
                    val pokemonDetailsViewModel: PokemonDetailsViewModel = hiltViewModel()
                    val name = it.arguments?.getString("name")

                    if (name != null) {
                        pokemonDetailsViewModel.setCurrentPokemonKey(name)
                        PokemonDetailsScreen(pokemonDetailsViewModel,
                            onBackButtonClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            })
        }
    }
}

