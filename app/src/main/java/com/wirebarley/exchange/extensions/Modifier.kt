package com.wirebarley.exchange.extensions

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.addFocusCleaner(
    focusManager: FocusManager,
    clearAction: () -> Unit = {}
) : Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(
            onTap = {
                clearAction()
                focusManager.clearFocus()
            }
        )
    }
}