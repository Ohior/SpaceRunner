package ohior.app.pear.draw_object

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp
import ohior.app.pear.core.PearShape


fun DrawScope.drawPearRect(pearShape: PearShape, color: Color) {
    drawRect(
        color = color,
        size = pearShape.pearOffset.toSize(),
        topLeft = pearShape.pearOffset.toOffset()
    )
}


fun DrawScope.drawPearOval(pearShape: PearShape, color: Color) {
    drawOval(
        color = color,
        size = pearShape.pearOffset.toSize(),
        topLeft = pearShape.pearOffset.toOffset()
    )
}

@Composable
fun DrawShape(pearShape: PearShape, color: Color) {
//    Canvas(
//        modifier = Modifier
//            .size(pearShape.pearOffset.width.dp, pearShape.pearOffset.height.dp)
//            .offset(pearShape.pearOffset.x.dp, pearShape.pearOffset.y.dp)
//    ) {
//        val center = Offset(size.width / 2, size.height / 2)
//        withTransform({
//            scale(2f, 0.5f) // Rotate around center
//        }) {
//            drawRect(
//                color = color,
//                size = pearShape.pearOffset.toSize(),
//                topLeft = pearShape.pearOffset.toOffset()
//            )
//        }
//    }
    Box(
        Modifier
            .offset(pearShape.pearOffset.x.dp, pearShape.pearOffset.y.dp)
            .drawWithCache {
                onDrawBehind {
                    withTransform({
//            scale(2f, 0.5f) // Rotate around center
                    }) {
                        drawRect(
                            color = color,
                            size = pearShape.pearOffset.toSize(),
                            topLeft = Offset.Zero,// don't change
                        )
                    }
                }
            }
    )
}