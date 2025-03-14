package org.spacerunner.game.screens.level1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.model.ScreenModel
import ohior.app.pear.utils.PearVector
import ohior.app.pearplatform.getPearWindowSize
import org.spacerunner.game.game_object.Bullet
import org.spacerunner.game.game_object.Crab
import org.spacerunner.game.game_object.Ground
import org.spacerunner.game.game_object.Robot

class Level1ScreenModel : ScreenModel {
    private val windowSize = getPearWindowSize()



    @Composable
    fun getBullet() = remember{ Bullet(PearVector.Zero) }

    @Composable
    fun getGround() = remember{
        Ground(
            PearVector(
                0f,
                windowSize.maxSize.height - 100,
                windowSize.maxSize.width, 100f,
                "ground"
            )
        )
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