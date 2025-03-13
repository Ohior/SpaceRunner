package org.spacerunner.game.screens.level1

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import ohior.app.pear.PearGameEngine
import ohior.app.pear.utils.PearVector
import ohior.app.pear.utils.pearBackground
import ohior.app.pearplatform.getPearWindowSize
import org.jetbrains.compose.resources.painterResource
import spacerunner.composeapp.generated.resources.Res
import spacerunner.composeapp.generated.resources.background3

object Level1Screen : Screen {
    @Composable
    override fun Content() {
        val viewmodel = rememberScreenModel { Level1ScreenModel() }
        val windowSize = getPearWindowSize()
        val robot = viewmodel.getRobot()
        val enemyCrab = viewmodel.getCrab()


        Scaffold { pv ->
            PearGameEngine(
                Modifier
                    .padding(pv)
                    .fillMaxSize()
                    .pearBackground(
                        painter = painterResource(Res.drawable.background3),
                        size = windowSize.maxSize
                    ),
                onUpdate = {
                    robot.update(viewmodel.ground.groundVector)
                    enemyCrab.update(viewmodel.ground.groundVector)
                },
                onContent = {
                    robot.DrawRobot()
                    enemyCrab.DrawCrab()
                    Box(
                        Modifier
                            .offset(
                                viewmodel.ground.groundVector.x.dp,
                                viewmodel.ground.groundVector.y.dp
                            )
                            .size(viewmodel.ground.groundVector.width.dp)
                            .background(Color(74, 6, 1))
                    )
                    // JUMP BUTTON
                    Box(
                        Modifier
                            .align(Alignment.BottomStart)
                            .size(100.dp)
                            .border(width = 3.dp, color = Color.Black, shape = CircleShape)
                            .clip(CircleShape)
                            .clickable {
                                robot.jump()
                            })
                    // SHOOT BUTTON
                    Box(
                        Modifier
                            .align(Alignment.BottomEnd)
                            .size(100.dp)
                            .border(width = 3.dp, color = Color.Black, shape = CircleShape)
                            .clip(CircleShape)
                            .clickable {
                                viewmodel.bullets.add(
                                    PearVector(
                                        robot.pearVector.x,
                                        robot.pearVector.y - (robot.pearVector.height / 3),
                                        10f,
                                        10f,
                                        "bullet"
                                    )
                                )
                            })
                    viewmodel.DrawBullets(Modifier)
                }
            )
        }
    }

}
