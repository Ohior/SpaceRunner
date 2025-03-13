package ohior.app.pearplatform

import androidx.compose.ui.geometry.Size
import kotlinx.browser.window


actual fun getPearPlatform(): PearPlatformType = PearPlatformType.WEB

actual fun getPearWindowSize(): PearWindowSize = PearWindowSize(
    Size(
        window.innerWidth.toFloat(),
        window.innerHeight.toFloat(),
    ),
    Size(
        window.outerWidth.toFloat(),
        window.outerHeight.toFloat()
    )
)
