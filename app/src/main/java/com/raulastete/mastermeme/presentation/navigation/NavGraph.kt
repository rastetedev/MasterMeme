package com.raulastete.mastermeme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raulastete.mastermeme.presentation.feature.create_meme.CreateMemeScreen
import com.raulastete.mastermeme.presentation.feature.meme_list.MemeListScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MemeList) {
        composable<Routes.MemeList> {
            MemeListScreen(navigateToCreateMeme = { navController.navigate(Routes.CreateMeme) })
        }

        composable<Routes.CreateMeme> {
            CreateMemeScreen(navigateBack = { navController.navigateUp() })
        }
    }
}
