package org.spacerunner.game.game_object

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ohior.app.pear.core.PearShape
import ohior.app.pear.utils.PearVector

class Ground(
    vector: PearVector,
):PearShape(vector) {
    var groundVector = vector
        private set

    override fun update() {
    }
    @Composable
    fun DrawGround(){
        Box(
            Modifier
                .offset(
                    groundVector.x.dp,
                    groundVector.y.dp
                )
                .size(groundVector.width.dp)
                .background(Color(74, 6, 1))
        )
    }
}