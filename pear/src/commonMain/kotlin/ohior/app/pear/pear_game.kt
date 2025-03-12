package ohior.app.pear

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock


@Composable
fun PearGameEngine(
    modifier: Modifier = Modifier,
    onStart: () -> Unit = {},
    onUpdate: (deltaTime: Int) -> Unit,
    onRender: (DrawScope.() -> Unit)? = null,
    onContent: @Composable () -> Unit
) {
    val frameTime = remember { Clock.System.now().nanosecondsOfSecond }
    LaunchedEffect(null) {
        onStart()
        while (true) {
            val currentTime = Clock.System.now().nanosecondsOfSecond
            val deltaTime = currentTime - frameTime
            onUpdate(deltaTime)
//            delay(16)
        }
    }
    Box(modifier = modifier.drawBehind {
        if (onRender != null) {
            onRender()
        }
    }) {
        onContent()
    }
}