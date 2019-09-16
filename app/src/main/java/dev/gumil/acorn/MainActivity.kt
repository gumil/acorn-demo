package dev.gumil.acorn

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import com.nhaarman.acorn.android.AcornAppCompatActivity
import com.nhaarman.acorn.android.navigation.NavigatorProvider

class MainActivity : AcornAppCompatActivity() {

    private lateinit var rootView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        rootView = FrameLayout(this)
        setContentView(rootView)
        super.onCreate(savedInstanceState)
    }

    override fun provideNavigatorProvider(): NavigatorProvider {
        return DemoNavigatorProvider
    }

    override fun provideRootView(): ViewGroup {
        return rootView
    }
}
