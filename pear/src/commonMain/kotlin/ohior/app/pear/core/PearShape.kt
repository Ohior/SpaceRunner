package ohior.app.pear.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import ohior.app.pear.utils.PearVector

abstract class PearShape(vector: PearVector) {
    var pearOffset by mutableStateOf(vector)
        private set

    fun move(x: Float = 0f, y: Float = 0f) {
        pearOffset = pearOffset.offsetBy(Offset(x, y))
    }

    fun cleanup() {}
}