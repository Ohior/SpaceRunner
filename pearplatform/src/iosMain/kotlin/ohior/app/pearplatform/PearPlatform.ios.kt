package ohior.app.pearplatform

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size

@Composable
actual fun getPearPlatform(): PearPlatform {
    return PearPlatform(
        PearPlatformType.IOS,
        Size(
            UIScreen.mainScreen.bounds.size.width,
            UIScreen.mainScreen.bounds.size.height,
        ),
        Size(
            UIScreen.mainScreen.bounds.size.width,
            UIScreen.mainScreen.bounds.size.height,
        )

    )
}