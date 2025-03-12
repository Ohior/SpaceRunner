package ohior.app.pear.draw_object

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import ohior.app.pear.core.PearShape
import ohior.app.pear.core.PearSprites
import ohior.app.pear.utils.PearVector

@Composable
inline fun rememberPearShape(
    key: Any? = null,
    crossinline calculation: @DisallowComposableCalls () -> PearShape
): PearShape {
    val pearRect = remember(key, calculation)
    DisposableEffect(key) {
        onDispose {
            pearRect.cleanup() // Clean up resources when the composable is removed
        }
    }
    return pearRect
}


//@Composable
//fun <T : PearSprite> rememberPearSprite(
//    key: Any? = null,
//    vector: PearVector,
//    bitmap: ImageBitmap,
//    factory: (PearVector, ImageBitmap) -> T // Factory function to create an instance of T
//): T {
//    val pearSprite = remember(key) {
//        factory(vector, bitmap) // Use the factory to create an instance of T
//    }
//
//    DisposableEffect(key) {
//        onDispose {
//            pearSprite.cleanup() // Clean up resources when the composable is removed
//        }
//    }
//
//    return pearSprite
//}
//
//
@Composable
fun <T : PearSprites> rememberPearSprite(
    key: Any? = null,
    vector: PearVector,
    bitmaps: List<ImageBitmap>,
    factory: (PearVector, List<ImageBitmap>) -> T // Factory function to create an instance of T
): T {
    val pearSprite = remember(key) {
        factory(vector, bitmaps) // Use the factory to create an instance of T
    }

    DisposableEffect(key) {
        onDispose {
            pearSprite.cleanup() // Clean up resources when the composable is removed
        }
    }

    return pearSprite
}

