package ohior.app.pear.draw_object

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.toIntSize
import ohior.app.pear.core.PearSprite
import ohior.app.pear.core.PearSprites


fun DrawScope.drawPearImage(
    pearSprite: PearSprite,
    rotationAngle: Float? = null
) {
    withTransform({
        if (rotationAngle != null) {
            rotate(rotationAngle,
                Offset(
                    pearSprite.pearOffset.x + (pearSprite.pearOffset.width / 2),
                    pearSprite.pearOffset.y + (pearSprite.pearOffset.height / 2),
                )
            ) // Rotate around center
        }
    }) {
        drawImage(
            image = pearSprite.pearBitmap,
            dstOffset = IntOffset(
                pearSprite.pearOffset.x.toInt(),
                pearSprite.pearOffset.y.toInt()
            ),// IntOffset.Zero,//Offset(0f, 0f), // Draw at top-left
            dstSize = pearSprite.pearOffset.toSize()
                .toIntSize()// IntSize(size.width.toInt(), size.height.toInt()) // Scale to fit
        )

    }
}


fun DrawScope.drawPearImage(
    pearSprites: PearSprites,
    rotationAngle: Float? = null
) {
    withTransform({
        if (rotationAngle != null) {
            rotate(rotationAngle,
                Offset(
                    pearSprites.pearOffset.x + (pearSprites.pearOffset.width / 2),
                    pearSprites.pearOffset.y + (pearSprites.pearOffset.height / 2),
                )
            ) // Rotate around center
        }
    }) {
        drawImage(
            image = pearSprites.pearBitmap,
            dstOffset = IntOffset(
                pearSprites.pearOffset.x.toInt(),
                pearSprites.pearOffset.y.toInt()
            ),// IntOffset.Zero,//Offset(0f, 0f), // Draw at top-left
            dstSize = pearSprites.pearOffset.toSize()
                .toIntSize()// IntSize(size.width.toInt(), size.height.toInt()) // Scale to fit
        )

    }
}
