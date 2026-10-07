package com.example.who_is_that_pokemon.screens.common.model

data class SpecieDetailsState(
    val descriptions: List<PokemonDescriptionState>
) {
    fun getFormattedDescription(): String? {
        if (descriptions.isNotEmpty()) {
            return descriptions[DESCRIPTION_INDEX].text.replace(NEWLINE_CHAR, SPACE_CHAR)
                .replace(FORM_FEED_CHAR, SPACE_CHAR)
        }
        return null
    }

    companion object {
        const val DESCRIPTION_INDEX = 0
        private const val NEWLINE_CHAR = "\n"
        private const val FORM_FEED_CHAR = "\u000c"
        private const val SPACE_CHAR = " "
    }
}
