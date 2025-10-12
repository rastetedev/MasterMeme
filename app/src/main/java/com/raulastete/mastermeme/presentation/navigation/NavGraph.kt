package com.raulastete.mastermeme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.raulastete.mastermeme.presentation.feature.create_meme.CreateMemeScreen
import com.raulastete.mastermeme.presentation.feature.meme_list.MemeListScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MemeList) {
        composable<Routes.MemeList> {
            MemeListScreen(navigateToCreateMeme = { navController.navigate(Routes.CreateMeme(it)) })
        }

        composable<Routes.CreateMeme> {

            val route = it.toRoute<Routes.CreateMeme>()

            CreateMemeScreen(
                templateResourceId = route.templateResourceId,
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}
