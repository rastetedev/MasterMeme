package com.raulastete.mastermeme.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object MemeList

    @Serializable
    data class CreateMeme(
        val templateResourceId: Int
    )
}