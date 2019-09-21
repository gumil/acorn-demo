package dev.gumil.acorn.navigation

import com.nhaarman.acorn.android.presentation.ViewControllerFactory
import com.nhaarman.acorn.android.transition.SceneTransition
import com.nhaarman.acorn.android.transition.SceneTransitionFactory
import com.nhaarman.acorn.navigation.TransitionData
import com.nhaarman.acorn.presentation.Scene
import dev.gumil.acorn.home.HomeScene
import dev.gumil.acorn.util.SegueTransition

internal class NavigationTransitionFactory(
    private val viewControllerFactory: ViewControllerFactory
) : SceneTransitionFactory {

    override fun supports(
        previousScene: Scene<*>,
        newScene: Scene<*>,
        data: TransitionData?
    ): Boolean {
        return (previousScene is NavigationScene || newScene is NavigationScene) &&
                previousScene !is HomeScene
    }

    override fun transitionFor(
        previousScene: Scene<*>,
        newScene: Scene<*>,
        data: TransitionData?
    ): SceneTransition {
        val isBackwards = newScene is NavigationScene && previousScene is NavigationScene &&
                newScene.index < previousScene.index
        return SegueTransition(
            if (data?.isBackwards == true || isBackwards) -1 else 1,
            { oldView, newView ->
                (oldView as? NavigationView)?.setButtonsEnabled(false)
                (newView as? NavigationView)?.setButtonsEnabled(false)
            },
            { oldView, newView ->
                (oldView as? NavigationView)?.setButtonsEnabled(true)
                (newView as? NavigationView)?.setButtonsEnabled(true)
            }) {
            viewControllerFactory.viewControllerFor(newScene, it)
        }
    }
}
