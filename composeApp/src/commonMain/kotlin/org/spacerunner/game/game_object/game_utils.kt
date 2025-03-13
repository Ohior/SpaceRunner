package org.spacerunner.game.game_object

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ohior.app.pear.utils.PearVector

//data class PearVector(
//    val x: Float,
//    val y: Float,
//    val width: Float,
//    val height: Float
//) {
//    fun toOffset() = Offset(x, y)
//    fun toSize() = Size(width, height)
//    companion object{
//        val Zero = PearVector(0f, 0f, 0f, 0f)
//    }
//}

abstract class PearGameObject(
    private val pearVector: PearVector,
    private val bitmaps: List<ImageBitmap>
) {
    abstract suspend fun update(other:PearVector)

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

@Composable
fun <T : PearGameObject> rememberPearGameObject(
    vector: PearVector,
    bitmaps: List<ImageBitmap> = emptyList(),
    key: Any? = null,
    factory: (PearVector, List<ImageBitmap>) -> T // Factory function to create an instance of T
): T {
    val pearSprite = remember(key) {
        factory(vector, bitmaps) // Use the factory to create an instance of T
    }
    return pearSprite
}


enum class BehaviorState{
    RUNNING, JUMPING, FALLING
}