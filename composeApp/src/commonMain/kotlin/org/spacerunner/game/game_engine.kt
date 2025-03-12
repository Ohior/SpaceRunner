package org.spacerunner.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

@Composable
fun PearGameEngine(
    modifier: Modifier = Modifier,
    onUpdate: suspend CoroutineScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    LaunchedEffect(null){
        while (true){
            onUpdate()
            delay(32)
        }
    }
    Box (modifier = modifier, content = { content() })
}