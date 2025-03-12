package ohior.app.pearplatform

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.toSize
import java.awt.Toolkit


@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getPearPlatform(): PearPlatform {
    return PearPlatform(
        PearPlatformType.DESKTOP,
        LocalWindowInfo.current.containerSize.toSize(),
//        Size(800f, 600f),
        Size(
            Toolkit.getDefaultToolkit().screenSize.width.toFloat(),
            Toolkit.getDefaultToolkit().screenSize.height.toFloat()
        )
    )
}