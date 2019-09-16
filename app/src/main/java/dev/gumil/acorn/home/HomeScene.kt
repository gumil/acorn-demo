package dev.gumil.acorn.home

import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import com.nhaarman.acorn.android.presentation.RestorableViewController
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.presentation.ViewProvidingScene
import dev.gumil.acorn.R

internal class HomeScene : ViewProvidingScene<HomeViewController> {

    override fun createViewController(parent: ViewGroup): ViewController {
        return HomeViewController(HomeView(parent.context, DemoModel.values().toList()))
    }
}

internal class HomeViewController(
    override val view: View
) : RestorableViewController

internal enum class DemoModel(
    val title: String,
    @param:ColorRes val color: Int
) {
    NAVIGATION("Navigation Demos", R.color.red_300),
    TRANSITIONS("Transition Demos", R.color.blue_grey_300),
    SHARED_ELEMENT_TRANSITIONS("Shared Element Demos", R.color.purple_300),
    CHILD_CONTROLLERS("Child Controllers", R.color.orange_300),
    VIEW_PAGER("ViewPager", R.color.green_300),
    TARGET_CONTROLLER("Target Controller", R.color.pink_300),
    MULTIPLE_CHILD_ROUTERS("Multiple Child Routers", R.color.deep_orange_300),
    MASTER_DETAIL("Master Detail", R.color.grey_300),
    DRAG_DISMISS("Drag Dismiss", R.color.lime_300),
}