package dev.gumil.acorn.transition

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.addListener
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.transition.SceneTransition
import dev.gumil.acorn.util.doOnPreDraw


internal class SegueTransition(
    private val direction: Int,
    private val onAnimationStart: (oldView: View, newView: View) -> Unit = { _, _ -> },
    private val onAnimationEnd: (oldView: View?, newView: View) -> Unit = { _, _ -> },
    private val viewController: (ViewGroup) -> ViewController
) : SceneTransition {
    override fun execute(parent: ViewGroup, callback: SceneTransition.Callback) {
        val originalChildren = (0..parent.childCount).map { parent.getChildAt(it) }
        val originalView = originalChildren.firstOrNull()

        val newViewController = viewController(parent)
        val newView = newViewController.view

        if (originalView == null) {
            parent.addView(newView)
            onAnimationEnd(originalView, newView)
            callback.onComplete(newViewController)
            return
        }

        parent.addView(newView, 0)

        callback.attach(newViewController)

        parent.doOnPreDraw {
            val fromTranslation = -1 * direction * originalView.width
            val toTranslation = direction * newView.width

            val set = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(originalView, "translationX", fromTranslation.toFloat()))
                play(ObjectAnimator.ofFloat(newView, "translationX", toTranslation.toFloat(), 0f))

                addListener(onEnd = {
                    originalChildren.forEach { child -> parent.removeView(child) }
                    onAnimationEnd(originalView, newView)
                    callback.onComplete(newViewController)
                })
            }

            onAnimationStart(originalView, newView)
            set.start()
        }
    }
}
