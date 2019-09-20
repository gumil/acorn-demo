package dev.gumil.acorn

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import com.nhaarman.acorn.android.AcornAppCompatActivity
import com.nhaarman.acorn.android.navigation.NavigatorProvider
import com.nhaarman.acorn.android.presentation.ViewControllerFactory
import com.nhaarman.acorn.android.transition.ComposingSceneTransitionFactory
import com.nhaarman.acorn.android.transition.SceneTransitionFactory
import dev.gumil.acorn.navigation.NavigationTransitionFactory

class MainActivity : AcornAppCompatActivity(), ToolbarScreen {

    private lateinit var rootView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        rootView = FrameLayout(this)
        setContentView(rootView)
        super.onCreate(savedInstanceState)
    }

    override fun provideNavigatorProvider(): NavigatorProvider {
        return DemoNavigatorProvider(ToolbarNavigatorListener(this))
    }

    override fun provideRootView(): ViewGroup {
        return rootView
    }

    override fun provideTransitionFactory(viewControllerFactory: ViewControllerFactory): SceneTransitionFactory {
        return ComposingSceneTransitionFactory.from(
            NavigationTransitionFactory(viewControllerFactory)
        )
    }

    override fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }
}

interface ToolbarScreen {
    fun setToolbarTitle(title: String)
}
