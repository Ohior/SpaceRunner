package ohior.app.pearplatform

import androidx.compose.ui.geometry.Size


actual fun getPearPlatform(): PearPlatformType = PearPlatformType.IOS

actual fun getPearWindowSize(): PearWindowSize = PearWindowSize(
    Size(
        UIScreen.mainScreen.bounds.size.width,
        UIScreen.mainScreen.bounds.size.height,
    ),
    Size(
        UIScreen.mainScreen.bounds.size.width,
        UIScreen.mainScreen.bounds.size.height,
    )
)