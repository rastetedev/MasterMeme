package com.raulastete.mastermeme.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object MemeList

    @Serializable
    object CreateMeme
}