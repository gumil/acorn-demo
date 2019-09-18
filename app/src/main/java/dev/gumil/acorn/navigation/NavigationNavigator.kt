package dev.gumil.acorn.navigation

import com.nhaarman.acorn.navigation.StackNavigator
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.NavigatorState
import com.nhaarman.acorn.state.SceneState
import dev.gumil.acorn.DemoNavigatorEvents
import dev.gumil.acorn.home.DemoModel
import kotlin.reflect.KClass

internal class NavigationNavigator(
    private val listener: DemoNavigatorEvents,
    savedState: NavigatorState?
) : StackNavigator(savedState), NavigationScene.Events {

    override fun initialStack(): List<Scene<out Container>> {
        return listOf(NavigationScene(this))
    }

    override fun instantiateScene(
        sceneClass: KClass<out Scene<*>>,
        state: SceneState?
    ): Scene<out Container> {
        return when (sceneClass) {
            NavigationScene::class -> NavigationScene.create(this, state)
            else -> error("Unknown scene: $sceneClass")
        }
    }

    override fun popToRoot() {
        finish()
    }

    override fun up() {
        listener.replace(DemoModel.NAVIGATION)
    }

    override fun next(index: Int, displayUpMode: DisplayUpMode) {
        push(NavigationScene(this, index, displayUpMode))
    }
}
