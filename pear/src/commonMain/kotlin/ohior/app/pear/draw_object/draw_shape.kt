package ohior.app.pear.draw_object

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
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
