package dev.gumil.acorn

import com.nhaarman.acorn.android.navigation.AbstractNavigatorProvider
import com.nhaarman.acorn.navigation.CompositeStackNavigator
import com.nhaarman.acorn.navigation.Navigator
import com.nhaarman.acorn.navigation.SavableNavigator
import com.nhaarman.acorn.navigation.TransitionData
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.Scene
import com.nhaarman.acorn.state.NavigatorState
import dev.gumil.acorn.home.DemoModel
import dev.gumil.acorn.home.HomeNavigator
import dev.gumil.acorn.navigation.NavigationNavigator
import dev.gumil.acorn.navigation.NavigationScene
import kotlin.reflect.KClass

internal class DemoNavigatorProvider(
    private val listener: ToolbarNavigatorListener
) : AbstractNavigatorProvider<DemoNavigator>() {

    override fun createNavigator(savedState: NavigatorState?): DemoNavigator {
        return DemoNavigator(savedState).apply { addNavigatorEventsListener(listener) }
    }
}

internal class ToolbarNavigatorListener(
    private val toolbarScreen: ToolbarScreen
) : Navigator.Events {
    override fun finished() {
        // do nothing
    }

    override fun scene(scene: Scene<out Container>, data: TransitionData?) {
        val title = when (scene) {
            is NavigationScene -> DemoModel.NAVIGATION.title
            else -> "Acorn Demo"
        }

        toolbarScreen.setToolbarTitle(title)
    }
}

internal class DemoNavigator(
    savedState: NavigatorState?
) : CompositeStackNavigator(savedState), SavableNavigator,
    DemoNavigatorEvents {

    override fun initialStack(): List<Navigator> {
        return listOf(HomeNavigator(this, null))
    }

    override fun instantiateNavigator(
        navigatorClass: KClass<out Navigator>,
        state: NavigatorState?
    ): Navigator {
        return when (navigatorClass) {
            HomeNavigator::class -> HomeNavigator(this, state)
            NavigationNavigator::class -> NavigationNavigator(this, state)
            else -> error("Could not instantiate navigator for class $navigatorClass.")
        }
    }

    override fun push(demoModel: DemoModel) {
        push(getNavigator(demoModel))
    }

    override fun replace(demoModel: DemoModel) {
        replace(getNavigator(demoModel))
    }

    private fun getNavigator(demoModel: DemoModel): Navigator {
        return when (demoModel) {
            DemoModel.NAVIGATION -> NavigationNavigator(this, null)
            DemoModel.TRANSITIONS -> TODO()
            DemoModel.SHARED_ELEMENT_TRANSITIONS -> TODO()
            DemoModel.CHILD_CONTROLLERS -> TODO()
            DemoModel.VIEW_PAGER -> TODO()
            DemoModel.TARGET_CONTROLLER -> TODO()
            DemoModel.MULTIPLE_CHILD_ROUTERS -> TODO()
            DemoModel.MASTER_DETAIL -> TODO()
            DemoModel.DRAG_DISMISS -> TODO()
        }
    }
}

internal interface DemoNavigatorEvents {
    fun push(demoModel: DemoModel)
    fun replace(demoModel: DemoModel)
}
