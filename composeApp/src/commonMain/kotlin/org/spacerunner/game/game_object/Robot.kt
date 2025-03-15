package org.spacerunner.game.game_object

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ohior.app.pear.core.PearSprites
import ohior.app.pear.utils.PearVector
import ohior.app.pear.utils.collideWith
import org.jetbrains.compose.resources.imageResource
import org.spacerunner.game.utils.BehaviorState
import spacerunner.composeapp.generated.resources.Res
import spacerunner.composeapp.generated.resources.robot1
import spacerunner.composeapp.generated.resources.robot2
import spacerunner.composeapp.generated.resources.robot3
import spacerunner.composeapp.generated.resources.robot4
import spacerunner.composeapp.generated.resources.robot5
import spacerunner.composeapp.generated.resources.robot6
import spacerunner.composeapp.generated.resources.robot7

class Robot(vector: PearVector, bitmaps: List<ImageBitmap>) :
    PearSprites(vector, bitmaps) {

    private val robotBitmap = animateFlow().stateIn(
        CoroutineScope(Dispatchers.Main),
        SharingStarted.WhileSubscribed(5000),
        bitmaps.first()
    )


    private var behaviorState = BehaviorState.FALLING
    var robotVector by mutableStateOf(vector)
    private var force: Float = 0f

    override suspend fun update(pearVectors: List<PearVector>) {
        when (behaviorState) {
            BehaviorState.RUNNING -> {
//                if (robotVector.x + (robotVector.width * 2) > robotVector.width)
//                    robotVector = robotVector.copy(x = robotVector.x - 1)
            }

            BehaviorState.JUMPING -> {
                force -= 5
                if (force <= 0) {
                    force = 0f
                    behaviorState = BehaviorState.FALLING
                }
                robotVector = robotVector.copy(y = robotVector.y - force)
            }

            BehaviorState.FALLING -> {
                force += 2
                if (force > 10) force = 10f
                robotVector = robotVector.copy(y = robotVector.y + force)
            }
        }
        val collisions = robotVector.collideWith(pearVectors)
        if (collisions.any { it.tag.lowercase() == "ground" }){
            behaviorState = BehaviorState.RUNNING
        }
        if (collisions.any { it.tag.lowercase() == "crab" }){
            robotVector = robotVector.copy(y = 100f)
            behaviorState = BehaviorState.FALLING
        }
    }

    fun jump() {
        if (behaviorState == BehaviorState.RUNNING) {
            behaviorState = BehaviorState.JUMPING
            force = 50f
        }
    }


    @Composable
    fun DrawRobot() {
        val rb = robotBitmap.collectAsState()
        Image(
            bitmap = rb.value,
            contentDescription = "Robot",
            modifier = Modifier
                .offset(robotVector.x.dp, robotVector.y.dp)
                .size(robotVector.width.dp)
                .border(width = 1.dp, color = Color.Red)
        )
    }

    companion object {
        val robotBitmaps
            @Composable get() = listOf(
                imageResource(Res.drawable.robot1),
                imageResource(Res.drawable.robot2),
                imageResource(Res.drawable.robot3),
                imageResource(Res.drawable.robot4),
                imageResource(Res.drawable.robot5),
                imageResource(Res.drawable.robot6),
                imageResource(Res.drawable.robot7),
            )
    }
}