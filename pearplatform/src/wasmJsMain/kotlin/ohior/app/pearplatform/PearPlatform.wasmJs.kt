package ohior.app.pearplatform

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import kotlinx.browser.window


@Composable
actual fun getPearPlatform(): PearPlatform = PearPlatform(
    PearPlatformType.WEB,
    Size(
        window.innerWidth.toFloat(),
        window.innerHeight.toFloat(),
    ),
    Size(
        window.outerWidth.toFloat(),
        window.outerHeight.toFloat()
    )
)