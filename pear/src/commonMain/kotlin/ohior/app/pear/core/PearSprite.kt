package ohior.app.pear.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ohior.app.pear.utils.PearVector


abstract class PearSprite(
    vector: PearVector,
    bitmap: ImageBitmap,
) {
    var pearOffset by mutableStateOf(vector)
        private set
    val pearBitmap = bitmap


    fun move(x: Float = 0f, y: Float = 0f) {
        pearOffset = pearOffset.offsetBy(Offset(x, y))
    }

    fun setPearVector(x: Float = 0f, y: Float = 0f) {
        pearOffset = pearOffset.copy(x = x, y = y)
    }

    fun cleanup() {}
}

abstract class PearSprites(
    vector: PearVector,
    bitmaps: List<ImageBitmap>,
) {
    var pearOffset by mutableStateOf(vector)
        private set
    var pearBitmap by mutableStateOf(bitmaps[0])
        private set
    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        scope.launch {
            var counter = 0
            while (true) {
                pearBitmap = bitmaps[counter]
                delay(100)
                counter++
                if (counter >= bitmaps.size) {
                    counter = 0
                }
            }
        }
    }

    fun cleanup() {}
    fun move(x: Float = 0f, y: Float = 0f) {
        pearOffset = pearOffset.offsetBy(Offset(x, y))
    }

    abstract fun update()

    fun setPearVector(x: Float = 0f, y: Float = 0f) {
        pearOffset = pearOffset.copy(x = x, y = y)
    }
}
