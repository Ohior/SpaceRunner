package org.spacerunner.game.screens.level1

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.model.ScreenModel
import ohior.app.pear.utils.PearVector
import ohior.app.pearplatform.getPearWindowSize
import org.spacerunner.game.game_object.Crab
import org.spacerunner.game.game_object.Ground
import org.spacerunner.game.game_object.Robot

class Level1ScreenModel : ScreenModel {
    private val windowSize = getPearWindowSize()
    val ground = Ground(
        PearVector(
            0f,
            windowSize.maxSize.height - 100,
            windowSize.maxSize.width, 100f,
            "ground"
        )
    )
    val bullets = mutableStateListOf<PearVector>()



    @Composable
    fun DrawBullets(modifier: Modifier){
        bullets.forEach { bullet->
            Canvas(modifier){
                drawOval(
                    color = Color.Red,
                    topLeft = bullet.toOffset(),
                    size = bullet.toSize()
                )
            }
        }
    }

    @Composable
    fun getRobot() = Robot(
        PearVector(100f, 100f, 100f, 100f, "robot"),
        Robot.robotBitmaps
    )

    @Composable
    fun getCrab() = Crab(
        PearVector(1000f, 100f, 100f, 100f, "crab"),
        Crab.crabBitmaps
    )
}