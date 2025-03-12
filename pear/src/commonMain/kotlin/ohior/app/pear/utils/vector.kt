package ohior.app.pear.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlin.math.abs


data class PearVector(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    val tag: String = ""
) {
    fun toOffset() = Offset(x, y)

    fun toSize() = Size(width, height)

    fun offsetBy(offset: Offset) = copy(
        x = x + offset.x,
        y = y + offset.y
    )

    fun toDpOffset() = DpOffset(x.dp, y.dp)

    fun toDpSize() = DpSize(width.dp, height.dp)
}

