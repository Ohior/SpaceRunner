package ohior.app.pear.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ohior.app.pear.utils.PearVector

abstract class PearShape(vector: PearVector) {
    var pearOffset by mutableStateOf(vector)
        private set

    abstract fun update()
}