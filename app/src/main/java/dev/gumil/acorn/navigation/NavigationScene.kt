package dev.gumil.acorn.navigation

import android.view.View
import android.view.ViewGroup
import com.nhaarman.acorn.android.presentation.RestorableViewController
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.presentation.ViewProvidingScene
import com.nhaarman.acorn.presentation.BasicScene
import com.nhaarman.acorn.state.SceneState
import com.nhaarman.acorn.state.get

internal class NavigationScene(
    private val listener: Events,
    private val index: Int = 0,
    private val displayUpMode: DisplayUpMode = DisplayUpMode.SHOW_FOR_CHILDREN_ONLY,
    savedState: SceneState? = null
) : BasicScene<NavigationViewController>(savedState),
    ViewProvidingScene<NavigationViewController> {

    override fun createViewController(parent: ViewGroup): ViewController {
        return NavigationViewController(NavigationView(parent.context, index, displayUpMode) {
            when (it) {
                NavigationView.NavEvents.PopToRoot -> listener.popToRoot()
                NavigationView.NavEvents.Up -> listener.up()
                is NavigationView.NavEvents.Next -> listener.next(it.index, it.displayUpMode)
            }
        })
    }

    override fun saveInstanceState(): SceneState {
        return super.saveInstanceState().apply {
            set(KEY_INDEX , index)
            set(KEY_DISPLAY , displayUpMode.ordinal)
        }
    }

    interface Events {
        fun popToRoot()
        fun up()
        fun next(index: Int, displayUpMode: DisplayUpMode)
    }

    companion object {
        private const val KEY_INDEX = "index"
        private const val KEY_DISPLAY = "displayUpMode"

        fun create(listener: Events, savedState: SceneState?): NavigationScene {
            val index = savedState?.get(KEY_INDEX) ?: 0
            val displayUpMode = DisplayUpMode.values()[savedState?.get(KEY_DISPLAY) ?:
            DisplayUpMode.SHOW_FOR_CHILDREN_ONLY.ordinal]

            return NavigationScene(listener, index, displayUpMode, savedState)
        }
    }
}

internal class NavigationViewController(
    override val view: View
) : RestorableViewController

internal enum class DisplayUpMode {
    SHOW,
    SHOW_FOR_CHILDREN_ONLY,
    HIDE;

    val displayUpModeForChild: DisplayUpMode
        get() {
            return when (this) {
                HIDE -> HIDE
                else -> SHOW
            }
        }
}
