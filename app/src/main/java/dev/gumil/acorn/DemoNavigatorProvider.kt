package dev.gumil.acorn

import com.nhaarman.acorn.android.navigation.AbstractNavigatorProvider
import com.nhaarman.acorn.navigation.CompositeStackNavigator
import com.nhaarman.acorn.navigation.Navigator
import com.nhaarman.acorn.state.NavigatorState
import dev.gumil.acorn.home.HomeNavigator
import kotlin.reflect.KClass

internal object DemoNavigatorProvider : AbstractNavigatorProvider<DemoNavigator>() {
    override fun createNavigator(savedState: NavigatorState?): DemoNavigator {
        return DemoNavigator(savedState)
    }
}

internal class DemoNavigator(savedState: NavigatorState?) : CompositeStackNavigator(savedState) {
    override fun initialStack(): List<Navigator> {
        return listOf(HomeNavigator(null))
    }

    override fun instantiateNavigator(
        navigatorClass: KClass<out Navigator>,
        state: NavigatorState?
    ): Navigator {
        return when (navigatorClass) {
            HomeNavigator::class -> HomeNavigator(state)
            else -> error("Could not instantiate navigator for class $navigatorClass.")
        }
    }
}
