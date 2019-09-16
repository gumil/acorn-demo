package dev.gumil.acorn.home

import com.nhaarman.acorn.navigation.StackNavigator
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.NavigatorState
import com.nhaarman.acorn.state.SceneState
import kotlin.reflect.KClass

internal class HomeNavigator(savedState: NavigatorState?) : StackNavigator(savedState) {
    override fun initialStack(): List<Scene<out Container>> {
        return listOf(HomeScene())
    }

    override fun instantiateScene(
        sceneClass: KClass<out Scene<*>>,
        state: SceneState?
    ): Scene<out Container> {
        return when (sceneClass) {
            HomeScene::class -> HomeScene()
            else -> error("Unknown scene: $sceneClass")
        }
    }
}
