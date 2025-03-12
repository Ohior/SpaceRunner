package ohior.app.pear.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun RoundButton(
    intOffset: IntOffset = IntOffset(10, 10),
    intSize: IntSize = IntSize(100,100),
    onTapped: (Offset) -> Unit = {}
){
    Box(
        modifier = Modifier
            .offset(intOffset.x.dp, intOffset.y.dp)
            .size(intSize.width.dp, intSize.height.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.1f)) // Transparent background
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = CircleShape
            )
            .pointerInput(null){
                detectTapGestures(
                    onTap = { onTapped(it) }
                )
            }
            .padding(horizontal = 24.dp, vertical = 12.dp), // Padding for the button
    )
}