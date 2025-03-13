package org.spacerunner.game.game_object

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toIntSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ohior.app.pear.utils.PearVector
import ohior.app.pearplatform.getPearWindowSize
import org.jetbrains.compose.resources.imageResource
import spacerunner.composeapp.generated.resources.Res
import spacerunner.composeapp.generated.resources.crab1
import spacerunner.composeapp.generated.resources.crab2
import spacerunner.composeapp.generated.resources.crab3
import spacerunner.composeapp.generated.resources.crab4
import spacerunner.composeapp.generated.resources.crab5
import spacerunner.composeapp.generated.resources.crab6

class Crab(vector: PearVector, bitmaps: List<ImageBitmap>) :
    PearGameObject(vector, bitmaps) {

    private val crabBitmap = animateFlow().stateIn(
        CoroutineScope(Dispatchers.Main),
        SharingStarted.WhileSubscribed(5000),
        bitmaps.first()
    )
    private var wSize = getPearWindowSize()
    private var behaviorState = BehaviorState.FALLING
    private var pearVector by mutableStateOf(vector)
    private var force: Float = 0f

    override suspend fun update(other: PearVector) {
        when (behaviorState) {
            BehaviorState.RUNNING -> {
                pearVector = pearVector.copy(x = pearVector.x - 10)
                if (pearVector.x+pearVector.width < 0f){
                    pearVector = pearVector.copy(x = wSize.maxSize.width)
                }
            }
            BehaviorState.JUMPING -> Unit
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
    }


    @Composable
    fun DrawCrab() {
        val rb = crabBitmap.collectAsState()
        Box(
            Modifier
                .offset(pearVector.x.dp, pearVector.y.dp)
                .size(pearVector.width.dp)
                .drawBehind {
                    drawImage(
                        rb.value,
                        dstSize = pearVector.toSize().toIntSize()
                    )
                }
                .border(width = 1.dp, color = Color.Red)
        )
    }

    companion object {
        val crabBitmaps
            @Composable get() = listOf(
                imageResource(Res.drawable.crab1),
                imageResource(Res.drawable.crab2),
                imageResource(Res.drawable.crab3),
                imageResource(Res.drawable.crab4),
                imageResource(Res.drawable.crab5),
                imageResource(Res.drawable.crab6),
            )
    }
}