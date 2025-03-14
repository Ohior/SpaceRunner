package ohior.app.pear.utils


sealed class PearCollision{
    data class Top(val tag:String = ""): PearCollision()
    data class Bottom(val tag:String = ""): PearCollision()
    data class Left(val tag:String = ""): PearCollision()
    data class Right(val tag:String = ""): PearCollision()
    data object None: PearCollision()
}

fun PearVector.collideWithin(other: PearVector): PearCollision {
    return when {
        // Fully inside other from the right
        x + width > other.x + other.width &&
                x < other.x + other.width &&
                y + height > other.y &&
                y < other.y + other.height -> PearCollision.Right(other.tag)

        // Fully inside other from the left
        x < other.x &&
                x + width > other.x &&
                y + height > other.y &&
                y < other.y + other.height -> PearCollision.Left(other.tag)

        // Fully inside other from the bottom
        y + height > other.y + other.height &&
                y < other.y + other.height &&
                x + width > other.x &&
                x < other.x + other.width -> PearCollision.Bottom(other.tag)

        // Fully inside other from the top
        y < other.y &&
                y + height > other.y &&
                x + width > other.x &&
                x < other.x + other.width -> PearCollision.Top(other.tag)

        else -> PearCollision.None
    }
}

fun PearVector.collideWith(other: PearVector): PearCollision {
    return when {
        // Bottom collision
        y + height >= other.y &&
                y < other.y + other.height &&
                x + width > other.x &&
                x < other.x + other.width -> PearCollision.Bottom(other.tag)

        // Top collision
        y <= other.y + other.height &&
                y + height > other.y &&
                x + width > other.x &&
                x < other.x + other.width -> PearCollision.Top(other.tag)

        // Right collision
        x + width >= other.x &&
                x < other.x &&
                y + height > other.y &&
                y < other.y + other.height -> PearCollision.Right(other.tag)

        // Left collision
        x <= other.x + other.width &&
                x + width > other.x + other.width &&
                y + height > other.y &&
                y < other.y + other.height -> PearCollision.Left(other.tag)

        else -> PearCollision.None
    }
}

fun PearVector.collideWithList(others: List<PearVector>): PearCollision {
    for (other in others) {
        val collision = this.collideWith(other)
        if (collision != PearCollision.None) {
            return collision // Return first detected collision
        }
    }
    return PearCollision.None
}

fun PearVector.collideWithinList(others: List<PearVector>): PearCollision {
    for (other in others) {
        val collision = this.collideWithin(other)
        if (collision != PearCollision.None) {
            return collision // Return first detected collision
        }
    }
    return PearCollision.None
}

fun PearVector.collideWithin(others: List<PearVector>): Set<PearCollision> {
    return others.mapNotNull {
        val collision = this.collideWithin(it)
        if (collision != PearCollision.None) collision else null
    }.toSet()
}

fun PearVector.collideWith(others: List<PearVector>): Set<PearCollision> {
    return others.mapNotNull {
        val collision = this.collideWith(it)
        if (collision != PearCollision.None) collision else null
    }.toSet()
}

//
//sealed class PearCollision {
//    data class Top(val tag: String) : PearCollision()
//    data class Bottom(val tag: String) : PearCollision()
//    data class Left(val tag: String) : PearCollision()
//    data class Right(val tag: String) : PearCollision()
//    data class None(val tag: String) : PearCollision()
//}
////enum class PearCollision {
////    TOP,
////    BOTTOM,
////    LEFT,
////    RIGHT,
////    NONE,
////}
//
////fun PearVector.collideWithin(other: PearVector): PearCollision {
////    return if (
////        x + width >= other.x + other.width &&
////        x >= other.x
////    ) PearCollision.RIGHT
////    else if (
////        x <= other.x &&
////        x + width <= other.x + other.width
////    ) PearCollision.LEFT
////    else if (
////        y + height >= other.y + other.height &&
////        y >= other.y
////    ) PearCollision.BOTTOM
////    else if (
////        y <= other.y &&
////        y + height <= other.y + other.height
////    ) PearCollision.TOP
////    else PearCollision.NONE
////}
////
////fun PearVector.collideWithin(other: PearVector): PearCollision {
////    return when {
////        // Fully inside other from the right
////        x + width > other.x + other.width &&
////                x < other.x + other.width &&
////                y + height > other.y &&
////                y < other.y + other.height -> PearCollision.RIGHT
////
////        // Fully inside other from the left
////        x < other.x &&
////                x + width > other.x &&
////                y + height > other.y &&
////                y < other.y + other.height -> PearCollision.LEFT
////
////        // Fully inside other from the bottom
////        y + height > other.y + other.height &&
////                y < other.y + other.height &&
////                x + width > other.x &&
////                x < other.x + other.width -> PearCollision.BOTTOM
////
////        // Fully inside other from the top
////        y < other.y &&
////                y + height > other.y &&
////                x + width > other.x &&
////                x < other.x + other.width -> PearCollision.TOP
////
////        else -> PearCollision.NONE
////    }
////}
////
////
////fun PearVector.collideWith(other: PearVector): PearCollision {
////    return when {
////        // Bottom collision
////        y + height >= other.y &&
////                y < other.y + other.height &&
////                x + width > other.x &&
////                x < other.x + other.width -> PearCollision.BOTTOM
////
////        // Top collision
////        y <= other.y + other.height &&
////                y + height > other.y &&
////                x + width > other.x &&
////                x < other.x + other.width -> PearCollision.TOP
////
////        // Right collision
////        x + width >= other.x &&
////                x < other.x &&
////                y + height > other.y &&
////                y < other.y + other.height -> PearCollision.RIGHT
////
////        // Left collision
////        x <= other.x + other.width &&
////                x + width > other.x + other.width &&
////                y + height > other.y &&
////                y < other.y + other.height -> PearCollision.LEFT
////
////        else -> PearCollision.NONE
////    }
////}
////
////fun PearVector.collideWithList(others: List<PearVector>): PearCollision {
////    for (other in others) {
////        val collision = this.collideWith(other)
////        if (collision != PearCollision.NONE) {
////            return collision // Return first detected collision
////        }
////    }
////    return PearCollision.NONE
////}
////
////fun PearVector.collideWith(others: List<PearVector>): Set<PearCollision> {
////    return others.mapNotNull {
////        val collision = this.collideWith(it)
////        if (collision != PearCollision.NONE) collision else null
////    }.toSet()
////}
////
////
////fun PearVector.collideWithinList(others: List<PearVector>): PearCollision {
////    for (other in others) {
////        val collision = this.collideWithin(other)
////        if (collision != PearCollision.NONE) {
////            return collision // Return first detected collision
////        }
////    }
////    return PearCollision.NONE
////}
////
////fun PearVector.collideWithin(others: List<PearVector>): Set<PearCollision> {
////    return others.mapNotNull {
////        val collision = this.collideWithin(it)
////        if (collision != PearCollision.NONE) collision else null
////    }.toSet()
////}
////
//
//
//fun PearVector.collideWithin(other: PearVector): PearCollision {
//    return when {
//        // Fully inside other from the right
//        x + width > other.x + other.width &&
//                x < other.x + other.width &&
//                y + height > other.y &&
//                y < other.y + other.height -> PearCollision.Right(other.tag)
//
//        // Fully inside other from the left
//        x < other.x &&
//                x + width > other.x &&
//                y + height > other.y &&
//                y < other.y + other.height -> PearCollision.Left(other.tag)
//
//        // Fully inside other from the bottom
//        y + height > other.y + other.height &&
//                y < other.y + other.height &&
//                x + width > other.x &&
//                x < other.x + other.width -> PearCollision.Bottom(other.tag)
//
//        // Fully inside other from the top
//        y < other.y &&
//                y + height > other.y &&
//                x + width > other.x &&
//                x < other.x + other.width -> PearCollision.Top(other.tag)
//
//        else -> PearCollision.None("")
//    }
//}
//
//
//fun PearVector.collideWith(other: PearVector): PearCollision {
//    return when {
//        // Bottom collision
//        y + height >= other.y &&
//                y < other.y + other.height &&
//                x + width > other.x &&
//                x < other.x + other.width -> PearCollision.Bottom(other.tag)
//
//        // Top collision
//        y <= other.y + other.height &&
//                y + height > other.y &&
//                x + width > other.x &&
//                x < other.x + other.width -> PearCollision.Top(other.tag)
//
//        // Right collision
//        x + width >= other.x &&
//                x < other.x &&
//                y + height > other.y &&
//                y < other.y + other.height -> PearCollision.Right(other.tag)
//
//        // Left collision
//        x <= other.x + other.width &&
//                x + width > other.x + other.width &&
//                y + height > other.y &&
//                y < other.y + other.height -> PearCollision.Left(other.tag)
//
//        else -> PearCollision.None("")
//    }
//}
//
//fun PearVector.collideWithList(others: List<PearVector>): PearCollision {
//    for (other in others) {
//        val collision = this.collideWith(other)
//        if (collision != PearCollision.None(other.tag)) {
//            return collision // Return first detected collision
//        }
//    }
//    return PearCollision.None("")
//}
//
//fun PearVector.collideWith(others: List<PearVector>): Set<PearCollision> {
//    return others.mapNotNull {
//        val collision = this.collideWith(it)
//        if (collision != PearCollision.None("")) collision else null
//    }.toSet()
//}
//
//
//fun PearVector.collideWithinList(others: List<PearVector>): PearCollision {
//    for (other in others) {
//        val collision = this.collideWithin(other)
//        if (collision != PearCollision.None(other.tag)) {
//            return collision // Return first detected collision
//        }
//    }
//    return PearCollision.None("")
//}
//
//fun PearVector.collideWithin(others: List<PearVector>): Set<PearCollision> {
//    return others.mapNotNull {
//        val collision = this.collideWithin(it)
//        if (collision != PearCollision.None("")) collision else null
//    }.toSet()
//}
//
