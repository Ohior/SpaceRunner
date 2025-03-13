package ohior.app.pear

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay


@Composable
fun PearGameEngine(
    modifier: Modifier = Modifier,
    onStart: () -> Unit = {},
    onUpdate: suspend CoroutineScope.() -> Unit,
    onRender: (DrawScope.() -> Unit)? = null,
    onContent: @Composable BoxScope.() -> Unit
) {
//    val frameTime = remember { Clock.System.now().nanosecondsOfSecond }
    LaunchedEffect(null) {
        onStart()
        while (true) {
//            val currentTime = Clock.System.now().nanosecondsOfSecond
//            val deltaTime = currentTime - frameTime
            onUpdate()
            delay(100)
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