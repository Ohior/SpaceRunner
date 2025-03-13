package ohior.app.pearplatform

import androidx.compose.ui.geometry.Size
import java.awt.Toolkit


actual fun getPearPlatform(): PearPlatformType = PearPlatformType.DESKTOP

actual fun getPearWindowSize(): PearWindowSize = PearWindowSize(
    Size(800f, 600f),
    Size(
        Toolkit.getDefaultToolkit().screenSize.width.toFloat(),
        Toolkit.getDefaultToolkit().screenSize.height.toFloat()
    )
)