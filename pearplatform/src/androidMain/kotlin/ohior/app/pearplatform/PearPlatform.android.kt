package ohior.app.pearplatform

import android.content.res.Resources
import androidx.compose.ui.geometry.Size

actual fun getPearPlatform(): PearPlatformType = PearPlatformType.ANDROID

actual fun getPearWindowSize(): PearWindowSize {
    val size = Resources.getSystem().displayMetrics
    return PearWindowSize(
        Size(size.widthPixels.toFloat(), size.heightPixels.toFloat()),
        Size(size.widthPixels.toFloat(), size.heightPixels.toFloat()),
    )
}