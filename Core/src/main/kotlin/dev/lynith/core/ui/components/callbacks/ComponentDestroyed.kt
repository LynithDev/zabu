package dev.lynith.core.ui.components.callbacks

import dev.lynith.core.ui.components.ComponentCallback

interface ComponentDestroyed : ComponentCallback {

    override fun invoke()

}
