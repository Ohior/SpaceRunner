package ohior.app.pear.utils

enum class PearCollisionState{
    NONE, TOP, BOTTOM, LEFT, RIGHT
}
data class PearCollision(
    val state: PearCollisionState,
    val tag: String = "",
    val isCollision: Boolean = false
)
//sealed class PearCollision {
//    data class Top(val tag: String = "") : PearCollision()
//    data class Bottom(val tag: String = "") : PearCollision()
//    data class Left(val tag: String = "") : PearCollision()
//    data class Right(val tag: String = "") : PearCollision()
//    data object None : PearCollision()
//}


// DEFAULT
fun PearVector.collideWithin(other: PearVector): PearCollision {
    return when {
        // Fully inside other from the right
        x + width > other.x + other.width &&
                x < other.x + other.width &&
                y + height > other.y &&
                y < other.y + other.height -> PearCollision(
                    PearCollisionState.RIGHT,
                    other.tag,
                    true
                )

        // Fully inside other from the left
        x < other.x &&
                x + width > other.x &&
                y + height > other.y &&
                y < other.y + other.height -> PearCollision(
                    PearCollisionState.LEFT,
                    other.tag,
                    true
                )

        // Fully inside other from the bottom
        y + height > other.y + other.height &&
                y < other.y + other.height &&
                x + width > other.x &&
                x < other.x + other.width -> PearCollision(
                    PearCollisionState.BOTTOM,
                    other.tag,
                    true
                )

        // Fully inside other from the top
        y < other.y &&
                y + height > other.y &&
                x + width > other.x &&
                x < other.x + other.width -> PearCollision(
                    PearCollisionState.TOP,
                    other.tag,
                    true
                )

        else -> PearCollision(
            PearCollisionState.NONE,
            other.tag,
            false
        )
    }
}

fun PearVector.collideWith(other: PearVector): PearCollision {
    return when {
        // Right collision
        x + width >= other.x &&
                x < other.x &&
                y + height > other.y &&
                y < other.y + other.height -> PearCollision(
                    PearCollisionState.RIGHT,
                    other.tag,
                    true
                )
        // Bottom collision
        y + height >= other.y &&
//                y < other.y + other.height &&
                x + width > other.x &&
                x < other.x + other.width -> PearCollision(
                    PearCollisionState.BOTTOM,
                    other.tag,
                    true
                )

        // Top collision
        y <= other.y + other.height &&
                y + height > other.y &&
                x + width > other.x &&
                x < other.x + other.width -> PearCollision(
                    PearCollisionState.TOP,
                    other.tag,
                    true
                )


        // Left collision
        x <= other.x + other.width &&
                x + width > other.x + other.width &&
                y + height > other.y &&
                y < other.y + other.height -> PearCollision(
                    PearCollisionState.LEFT,
                    other.tag,
                    true
                )

        else -> PearCollision(
            PearCollisionState.NONE,
            other.tag,
            false
        )
    }
}

// LIST
fun PearVector.collideWithList(others: List<PearVector>): PearCollision {
    for (other in others) {
        val collision = this.collideWith(other)
        if (collision != PearCollision(
                PearCollisionState.NONE,
                other.tag,
                false
        )) {
            return collision // Return first detected collision
        }
    }
    return PearCollision(
        PearCollisionState.NONE,
        "",
        false
    )
}

fun PearVector.collideWithinList(others: List<PearVector>): PearCollision {
    for (other in others) {
        val collision = this.collideWithin(other)
        if (collision != PearCollision(
                PearCollisionState.NONE,
                other.tag,
                false
        )) {
            return collision // Return first detected collision
        }
    }
    return PearCollision(
        PearCollisionState.NONE,
        "",
        false
    )
}


// SET
fun PearVector.collideWithin(others: List<PearVector>): Set<PearCollision> {
    return others.mapNotNull {
        val collision = this.collideWithin(it)
        if (collision != PearCollision(
                PearCollisionState.NONE,
                it.tag,
                false
        )) collision else null
    }.toSet()
}

fun PearVector.collideWith(others: List<PearVector>): Set<PearCollision> {
    return others.mapNotNull {
        val collision = this.collideWith(it)
        if (collision != PearCollision(
                PearCollisionState.NONE,
                it.tag,
                false
        )) collision else null
    }.toSet()
}
