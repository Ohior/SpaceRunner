package org.spacerunner.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toIntSize
import ohior.app.pear.utils.pearBackground
import ohior.app.pearplatform.getPearPlatform
import org.jetbrains.compose.resources.painterResource
import org.spacerunner.game.game_object.Crab
import org.spacerunner.game.game_object.PearVector
import org.spacerunner.game.game_object.Robot
import org.spacerunner.game.game_object.rememberPearGameObject
import org.spacerunner.game.theme.AppTheme
import spacerunner.composeapp.generated.resources.Res
import spacerunner.composeapp.generated.resources.background3


@Composable
internal fun App() = AppTheme {
    val windowSize = getPearPlatform()
    val robot = rememberPearGameObject(
        PearVector(100f, 100f, 50f, 50f),
        Robot.robotBitmaps
    ) { vector, bitmaps -> Robot(vector, bitmaps).apply { wSize = windowSize.maxSize } }
    val enemyCrab = rememberPearGameObject(
        PearVector(1000f, 100f, 100f, 100f),
        Crab.crabBitmaps
    ) { vector, bitmaps -> Crab(vector, bitmaps).apply { wSize = windowSize.maxSize } }
    val floor = remember {
        PearVector(
            0f, windowSize.maxSize.height - 100, windowSize.maxSize.width, 100f
        )
    }

    Scaffold { pv ->
        PearGameEngine(
            Modifier
                .padding(pv)
                .fillMaxSize()
                .pearBackground(
                    painter = painterResource(Res.drawable.background3),
                    size = windowSize.size
                ),
            onUpdate = {
                robot.update(floor)
                enemyCrab.update(floor)
            },
            content = {
                robot.DrawRobot()
                enemyCrab.DrawCrab()
                Box(
                    Modifier
                        .offset(floor.x.dp, floor.y.dp)
                        .size(floor.width.dp)
                        .background(Color(74, 6, 1))
                )
//                    .drawBehind {
//                    drawRect(
//                        color = Color(74, 6, 1),
//                        topLeft = floor.toOffset(),
//                        size = floor.toSize()
//                    )
//                })
                // JUMP
                Box(
                    Modifier
                        .align(Alignment.BottomStart)
                        .size(100.dp)
                        .border(width = 3.dp, color = Color.Black, shape = CircleShape)
                        .clip(CircleShape)
                        .clickable {
                            robot.jump()
                        })
                // SHOOT
                Box(
                    Modifier
                        .align(Alignment.BottomEnd)
                        .size(100.dp)
                        .border(width = 3.dp, color = Color.Black, shape = CircleShape)
                        .clip(CircleShape)
                        .clickable {
                            robot.shoot()
                        })
            }
        )
    }
}
//        Navigator(Level1Screen)
