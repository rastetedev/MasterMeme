package com.raulastete.mastermeme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.raulastete.mastermeme.navigation.NavGraph
import com.raulastete.mastermeme.ui.theme.MasterMemeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            MasterMemeTheme {
                NavGraph()
            }
        }
    }
}