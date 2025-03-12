package ohior.app.pearplatform

import android.content.Context
import android.content.res.Resources
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.getSystemService

@Composable
actual fun getPearPlatform(): PearPlatform {
//    val size = Resources.getSystem().displayMetrics
    val size = LocalContext.current.resources.displayMetrics
    return PearPlatform(
        PearPlatformType.ANDROID,
//        Size(
//            size.widthPixels.toFloat(),
//            size.heightPixels.toFloat()
//        ),
        Size(size.widthPixels.toFloat(), size.heightPixels.toFloat()),
        Size(
            LocalConfiguration.current.screenWidthDp.toFloat(),
            LocalConfiguration.current.screenHeightDp.toFloat()
        )
    )
}
