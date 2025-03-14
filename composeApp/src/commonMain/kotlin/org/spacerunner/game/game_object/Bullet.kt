package org.spacerunner.game.game_object

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ohior.app.pear.core.PearShape
import ohior.app.pear.utils.PearVector
import ohior.app.pearplatform.getPearWindowSize

class Bullet(
    vector: PearVector,
) : PearShape(vector) {
    val bullets = mutableStateListOf<PearVector>()
    private val windowSize = getPearWindowSize()

    override fun update() {
        val tempBullets = mutableListOf<PearVector>()
        for(index in bullets.indices){
            bullets[index] = bullets[index].copy(x = bullets[index].x + 10)
            if (bullets[index].x > windowSize.maxSize.width) tempBullets.add(bullets[index])
        }
        bullets.removeAll(tempBullets)
    }
    @Composable
    fun DrawBullets(modifier: Modifier) {
        bullets.forEach { b ->
            Canvas(modifier) {
                drawOval(
                    color = Color.Red,
                    topLeft = b.toOffset(),
                    size = b.toSize()
                )
            }
        }
    }
}