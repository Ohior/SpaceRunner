package ohior.app.pearplatform

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size

enum class PearPlatformType {
    ANDROID, DESKTOP, IOS, WEB
}

data class PearPlatform(
    val name: PearPlatformType,
    val size: Size,
    val maxSize: Size
)

@Composable
expect fun getPearPlatform(): PearPlatform