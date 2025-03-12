package ohior.app.pear.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun Modifier.pearBackground(
    painter: Painter,
    size: Size
): Modifier {
    return this.drawWithCache {
        onDrawBehind {
            with(painter){
                draw(size)
            }
        }
    }
}