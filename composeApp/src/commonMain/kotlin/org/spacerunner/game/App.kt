package org.spacerunner.game

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.spacerunner.game.screens.level1.Level1Screen
import org.spacerunner.game.theme.AppTheme


@Composable
internal fun App() = AppTheme {
    Navigator(Level1Screen)
}