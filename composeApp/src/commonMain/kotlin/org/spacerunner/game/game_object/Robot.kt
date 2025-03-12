package org.spacerunner.game.game_object

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toIntSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ohior.app.pearplatform.getPearPlatform
import org.jetbrains.compose.resources.imageResource
import spacerunner.composeapp.generated.resources.Res
import spacerunner.composeapp.generated.resources.robot1
import spacerunner.composeapp.generated.resources.robot2
import spacerunner.composeapp.generated.resources.robot3
import spacerunner.composeapp.generated.resources.robot4
import spacerunner.composeapp.generated.resources.robot5
import spacerunner.composeapp.generated.resources.robot6
import spacerunner.composeapp.generated.resources.robot7
import kotlin.random.Random

class Robot(vector: PearVector, bitmaps: List<ImageBitmap>) :
    PearGameObject(vector, bitmaps) {

    private val robotBitmap = animateFlow().stateIn(
        CoroutineScope(Dispatchers.Main),
        SharingStarted.WhileSubscribed(5000),
        bitmaps.first()
    )
    private val bullets = mutableStateListOf<PearVector>()

    var wSize = Size.Zero

    private var behaviorState = BehaviorState.FALLING
    private var pearVector by mutableStateOf(vector)
    private var force: Float = 0f

    override suspend fun update(other: PearVector) {
        when (behaviorState) {
            BehaviorState.RUNNING -> Unit
            BehaviorState.JUMPING -> {
                force -= 1
                if (force <= 0) {
                    force = 0f
                    behaviorState = BehaviorState.FALLING
                }
                pearVector = pearVector.copy(y = pearVector.y - force)
            }

            BehaviorState.FALLING -> {
                force += 1
                if (force > 10) force = 10f
                pearVector = pearVector.copy(y = pearVector.y + force)
            }
        }
        when (pearVector.collideWith(other)) {
            PearCollision.BOTTOM -> {
                behaviorState = BehaviorState.RUNNING
            }

            else -> Unit
        }
        for (index in 0 until bullets.size) {
            if (bullets[index].x > wSize.width) {
                bullets.removeAt(index)
                break
            } else {
                bullets[index] = bullets[index].copy(x = bullets[index].x + 10)//PearVector(bullets[index].x + 5, bullets[index].y, 10f, 10f)
            }
        }
    }

    fun jump() {
        if (behaviorState == BehaviorState.RUNNING) {
            behaviorState = BehaviorState.JUMPING
            force = 15f
        }
    }

    fun shoot() {
        bullets.add(PearVector(pearVector.x + (pearVector.width / 3), pearVector.y + (pearVector.height/2), 5f, 5f))
    }

    @Composable
    fun DrawRobot() {
        val rb = robotBitmap.collectAsState()
        Box(
            Modifier
                .offset(pearVector.x.dp, pearVector.y.dp)
                .size(pearVector.width.dp)
                .border(width = 3.dp, color = Color.Red)
                .drawBehind {
                    drawImage(
                        rb.value,
                        dstSize = pearVector.toSize().toIntSize()
                    )
                }
        )
        bullets.forEach { bullet ->
            Box(
                Modifier
                    .offset(bullet.x.dp, bullet.y.dp)
                    .size(bullet.width.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow)
            )
        }
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