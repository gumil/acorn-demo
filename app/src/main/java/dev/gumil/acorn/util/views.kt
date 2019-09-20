package dev.gumil.acorn.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewTreeObserver
import dev.gumil.acorn.R

internal class ResourceNotFoundException(message: String) : Throwable(message)

internal fun Context.getSelectableItemBackground(): Drawable {
    val typedArray = obtainStyledAttributes(intArrayOf(R.attr.selectableItemBackground))
    return typedArray.getDrawable(0).also {
        typedArray.recycle()
    } ?: throw ResourceNotFoundException("selectableItemBackground not found")
}

internal fun Context.getMaterialColor(index: Int): Int {
    val colors = resources.obtainTypedArray(R.array.mdcolor_300)

    return colors.getColor(index % colors.length(), Color.BLACK).apply {
        colors.recycle()
    }
}

internal inline fun View.doOnPreDraw(crossinline f: () -> Unit) {
    val viewTreeObserver = viewTreeObserver
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            f()
            when {
                viewTreeObserver.isAlive -> viewTreeObserver.removeOnPreDrawListener(this)
                else -> this@doOnPreDraw.viewTreeObserver.removeOnPreDrawListener(this)
            }
            return true
        }
    })
}
