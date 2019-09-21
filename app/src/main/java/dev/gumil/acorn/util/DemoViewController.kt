package dev.gumil.acorn.util

import android.view.View
import com.nhaarman.acorn.android.presentation.RestorableViewController

internal class DemoViewController<V: View>(
    val demoView: V
): RestorableViewController {
    override val view: View = demoView
}