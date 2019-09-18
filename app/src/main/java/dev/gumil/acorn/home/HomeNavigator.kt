package dev.gumil.acorn.home

import com.nhaarman.acorn.navigation.StackNavigator
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.NavigatorState
import com.nhaarman.acorn.state.SceneState
import dev.gumil.acorn.DemoNavigatorEvents
import kotlin.reflect.KClass

internal class HomeNavigator(
    private val events: DemoNavigatorEvents,
    savedState: NavigatorState?
) : StackNavigator(savedState), HomeScene.Events {

    override fun initialStack(): List<Scene<out Container>> {
        return listOf(HomeScene(this))
    }

    override fun instantiateScene(
        sceneClass: KClass<out Scene<*>>,
        state: SceneState?
    ): Scene<out Container> {
        return when (sceneClass) {
            HomeScene::class -> HomeScene(this)
            else -> error("Unknown scene: $sceneClass")
        }
    }

    override fun onItemClicked(demoModel: DemoModel) {
        events.push(demoModel)
    }
}
