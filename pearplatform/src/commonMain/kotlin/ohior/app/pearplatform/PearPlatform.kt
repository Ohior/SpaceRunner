package ohior.app.pearplatform

import androidx.compose.ui.geometry.Size

enum class PearPlatformType {
    ANDROID, DESKTOP, IOS, WEB
}

data class PearWindowSize(
    val defaultSize: Size,
    val maxSize: Size
)

expect fun getPearPlatform(): PearPlatformType

expect fun getPearWindowSize(): PearWindowSize