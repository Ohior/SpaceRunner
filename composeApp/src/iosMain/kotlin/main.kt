import androidx.compose.ui.window.ComposeUIViewController
import org.spacerunner.game.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
