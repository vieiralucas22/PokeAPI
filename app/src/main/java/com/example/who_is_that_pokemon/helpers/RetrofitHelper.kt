package com.example.who_is_that_pokemon.helpers

class RetrofitHelper {

    companion object {
        const val BASE_POKE_API_URL: String = "https://pokeapi.co/api/v2/"

        fun getNext20PokemonInfo(next20Pokemon: String?): Pair<Int, Int> {

            if (next20Pokemon.isNullOrBlank()) return 0 to 20

            val query =
                next20Pokemon.replace(BASE_POKE_API_URL, "")
                    .substringAfter("?", "")
            val params = query.split("&")
                .associate {
                    val (key, value) = it.split("=")
                    key to value
                }

            val offset = params["offset"]?.toIntOrNull() ?: 0
            val limit = params["limit"]?.toIntOrNull() ?: 20

            return offset to limit
        }
    }
}