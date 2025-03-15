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
import ohior.app.pearplatform.getPearWindowSize
import org.jetbrains.compose.resources.imageResource
import org.spacerunner.game.utils.BehaviorState
import spacerunner.composeapp.generated.resources.Res
import spacerunner.composeapp.generated.resources.crab1
import spacerunner.composeapp.generated.resources.crab2
import spacerunner.composeapp.generated.resources.crab3
import spacerunner.composeapp.generated.resources.crab4
import spacerunner.composeapp.generated.resources.crab5
import spacerunner.composeapp.generated.resources.crab6

class Crab(vector: PearVector, bitmaps: List<ImageBitmap>) :
    PearSprites(vector, bitmaps) {

    private val crabBitmap = animateFlow().stateIn(
        CoroutineScope(Dispatchers.Main),
        SharingStarted.WhileSubscribed(5000),
        bitmaps.first()
    )
    private var wSize = getPearWindowSize()
    private var hits = 9
    private var borderColor by mutableStateOf(Color.Green)
    private var behaviorState = BehaviorState.FALLING
    var crabVector by mutableStateOf(vector)
    private var force: Float = 0f

    override suspend fun update(pearVectors: List<PearVector>) {
        when (behaviorState) {
            BehaviorState.RUNNING -> {
                crabVector = crabVector.copy(x = crabVector.x - 10)
                if (crabVector.x + crabVector.width < 0f) {
                    crabVector = crabVector.copy(x = wSize.maxSize.width)
                }
            }

            BehaviorState.JUMPING -> Unit
            BehaviorState.FALLING -> {
                force += 1
                if (force > 10) force = 10f
                crabVector = crabVector.copy(y = crabVector.y + force)
            }
        }
        val collisions = crabVector.collideWith(pearVectors)
        if (collisions.any { it.tag.lowercase() == "ground" }){
            behaviorState = BehaviorState.RUNNING
        }
        if (collisions.any { it.tag.lowercase() == "bullet" }){
            hits -= 1
            if (hits/3 == 2) borderColor = Color.Yellow
            else if (hits/3 == 1) borderColor = Color.Red
            if (hits <= 0) {
                crabVector = crabVector.copy(y = 200f)
                behaviorState = BehaviorState.FALLING
                hits = 9
                crabVector = crabVector.copy(x = wSize.maxSize.width, y = wSize.maxSize.height-300)
                borderColor = Color.Green
            }
        }
    }


    @Composable
    fun DrawCrab() {
        val rb = crabBitmap.collectAsState()
        Image(
            bitmap = rb.value,
            contentDescription = "Crab",
            modifier = Modifier
                .offset(crabVector.x.dp, crabVector.y.dp)
                .size(crabVector.width.dp)
                .border(width = 1.dp, color = borderColor)
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