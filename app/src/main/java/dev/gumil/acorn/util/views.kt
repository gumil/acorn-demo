package dev.gumil.acorn.util

import android.content.Context
import android.graphics.drawable.Drawable
import dev.gumil.acorn.R

internal class ResourceNotFoundException(message: String) : Throwable(message)

internal fun Context.getSelectableItemBackground(): Drawable {
    val typedArray = obtainStyledAttributes(intArrayOf(R.attr.selectableItemBackground))
    return typedArray.getDrawable(0).also {
        typedArray.recycle()
    } ?: throw ResourceNotFoundException("selectableItemBackground not found")
}
