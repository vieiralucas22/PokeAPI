package com.example.who_is_that_pokemon.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.who_is_that_pokemon.R
import com.example.who_is_that_pokemon.screens.common.model.InitialPokemonState
import com.example.who_is_that_pokemon.screens.common.model.PokemonColorState
import com.example.who_is_that_pokemon.screens.common.model.PokemonState
import com.example.who_is_that_pokemon.screens.common.model.SpecieDetailsState
import com.example.who_is_that_pokemon.screens.common.model.SpritesState
import com.example.who_is_that_pokemon.dsm.animation.LoadingAnimation
import com.example.who_is_that_pokemon.dsm.composable.ErrorComponent
import com.example.who_is_that_pokemon.screens.home.composable.PokemonItem
import com.example.who_is_that_pokemon.screens.home.model.HomeUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onPokemonClick: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    HomeScaffold(uiState, onPokemonClick = onPokemonClick, onSearch = onSearch)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScaffold(
    uiState: HomeUIState,
    onPokemonClick: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    Scaffold(
        containerColor = colorResource(R.color.white),
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.white)
                ),
                title = {
                    Text(
                        text = "Pokedex",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    )
                })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 20.dp)
                    .navigationBarsPadding()
            ) {
                Content(uiState, onPokemonClick = onPokemonClick, onSearch = onSearch)
            }
        }
    )
}

@Composable
fun Content(
    uiState: HomeUIState,
    onPokemonClick: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    var pokemonSearch by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Search for a pokemon by name or using its National Number according pokedex.",
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Gray,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = pokemonSearch,
        onValueChange = { pokemonSearch = it },
        modifier = Modifier
            .height(56.dp)
            .background(colorResource(R.color.search_background), RoundedCornerShape(16.dp))
            .fillMaxWidth(),
        placeholder = { Text("Search Pokemon") },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        trailingIcon = {
            IconButton(onClick = { onSearch(pokemonSearch.lowercase().trim()) })
            {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        }, colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            focusedContainerColor = colorResource(R.color.search_background),
            unfocusedContainerColor = colorResource(R.color.search_background)
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

    when (uiState) {

        is HomeUIState.Success ->
            PokemonGrid(uiState.data) {
                onPokemonClick(it)
            }

        is HomeUIState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingAnimation(
                    circleSize = 30.dp,
                    spaceBetween = 20.dp,
                    travelDistance = 20.dp
                )
            }

        }

        is HomeUIState.Error -> ErrorComponent(uiState.message)
    }
}

@Composable
fun PokemonGrid(state: InitialPokemonState, onPokemonClick: (String) -> Unit) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {
        itemsIndexed(state.allPokemon) { _, pokemon ->
            PokemonItem(pokemon) { pokemonName ->
                onPokemonClick(pokemonName)
            }
        }
    }
}

@Preview(name = "LoadingState")
@Composable
private fun HomeLoadingStatePreview() {
    HomeScaffold(HomeUIState.Loading, onPokemonClick = {}, onSearch = {})
}

@Preview(name = "SuccessState")
@Composable
private fun HomeSuccessStatePreview() {
    val mockPokemon = PokemonState(
        id = 25,
        name = "pikachu",
        height = 0.4,
        weight = 6.0,
        types = emptyList(),
        sprites = SpritesState(
            default = "",
            shiny = ""
        ),
        stats = emptyList(),
        specieDetails = SpecieDetailsState(
            pokemonColor = PokemonColorState("yellow"),
            descriptions = emptyList()
        )
    )
    val mockState = InitialPokemonState(
        total = 1,
        allPokemon = listOf(mockPokemon),
        next20Pokemon = null
    )
    HomeScaffold(HomeUIState.Success(mockState), onPokemonClick = {}, onSearch = {})
}

@Preview(name = "ErrorState")
@Composable
private fun HomeErrorStatePreview() {
    HomeScaffold(HomeUIState.Error("Pokemon not found!"), onPokemonClick = {}, onSearch = {})
}
