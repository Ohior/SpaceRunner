package ohior.app.pear.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ohior.app.pear.utils.PearVector


abstract class PearSprite(
    pearVector: PearVector,
    bitmap: ImageBitmap,
) {
    var pearOffset by mutableStateOf(pearVector)
        private set
    val pearBitmap = bitmap
    abstract suspend fun update(pearVectors: List<PearVector>)
}

abstract class PearSprites(
    vector: PearVector,
    private val bitmaps: List<ImageBitmap>
) {
//    var pearOffset by mutableStateOf(vector)
//        private set
    var pearBitmap by mutableStateOf(bitmaps[0])
        private set

    abstract suspend fun update(pearVectors: List<PearVector>)

    fun animateFlow(): Flow<ImageBitmap> {
        return flow {
            var counter = 0
            while (true) {
                delay(100)
                counter++
                if (counter >= bitmaps.size) {
                    counter = 0
                }
                emit(bitmaps[counter])
            }
        }
    }
}
