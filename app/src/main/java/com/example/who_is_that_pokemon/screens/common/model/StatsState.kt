package com.example.who_is_that_pokemon.screens.common.model

data class StatsState(
    val value: Float,
    val stat: StatState
)

data class StatState(
    val statName: String,
) {
    fun getFormattedStatName(): String = this.statName.replaceFirstChar { it.uppercase() }
        .replace(SPECIAL_VALUE, SPECIAL_VALUE_TO_REPLACE)
        .replace(ATTACK_VALUE, ATTACK_VALUE_TO_REPLACE)
        .replace(DEFENSE_VALUE, DEFENSE_VALUE_TO_REPLACE)

    companion object {
        const val SPECIAL_VALUE = "Special-"
        const val ATTACK_VALUE = "attack"
        const val DEFENSE_VALUE = "defense"

        const val SPECIAL_VALUE_TO_REPLACE = "Sp."
        const val ATTACK_VALUE_TO_REPLACE = "Atk"
        const val DEFENSE_VALUE_TO_REPLACE = "Def"
    }
}