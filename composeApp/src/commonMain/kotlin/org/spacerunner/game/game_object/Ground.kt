package org.spacerunner.game.game_object

import ohior.app.pear.core.PearShape
import ohior.app.pear.utils.PearVector

class Ground(
    vector: PearVector,
):PearShape(vector) {
    var groundVector = vector
        private set
}