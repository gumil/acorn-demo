package dev.gumil.acorn.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import com.squareup.contour.ContourLayout
import dev.gumil.acorn.R
import dev.gumil.acorn.util.getMaterialColor
import dev.gumil.acorn.util.getSelectableItemBackground

@SuppressLint("ViewConstructor")
internal class NavigationView(
    context: Context,
    index: Int = 0,
    displayUpMode: DisplayUpMode = DisplayUpMode.SHOW_FOR_CHILDREN_ONLY,
    eventsListener: (NavEvents) -> Unit = {}
) : ContourLayout(context) {

    private val popButton = Button(context).apply {
        text = context.getString(R.string.pop_to_root)

        setOnClickListener {
            eventsListener(NavEvents.PopToRoot)
        }
        applyButtonBarStyle()
    }

    private val upButton = Button(context).apply {
        text = context.getString(R.string.go_up)
        visibility = if (displayUpMode != DisplayUpMode.SHOW) View.GONE else View.VISIBLE

        setOnClickListener {
            eventsListener(NavEvents.Up)
        }
        applyButtonBarStyle()
    }

    private val nextButton = Button(context).apply {
        text = context.getString(R.string.next_controller)

        setOnClickListener {
            eventsListener(NavEvents.Next(index + 1, displayUpMode.displayUpModeForChild))
        }
        applyButtonBarStyle()
    }

    init {
        setBackgroundColor(context.getMaterialColor(index))

        val buttonHeight = 72.dip

        LinearLayout(context).apply {
            setBackgroundColor(Color.WHITE)

            applyLayout(
                x = leftTo { parent.left() }
                    .rightTo { parent.right() },
                y = topTo { parent.bottom() - buttonHeight }
                    .bottomTo { parent.bottom() }
            )

            addView(popButton)
            addView(upButton)
            addView(nextButton)
        }

        TextView(context).apply {
            textSize = 20f
            text = context.getString(R.string.navigation_title, index)
            applyLayout(
                x = centerHorizontallyTo { parent.centerX() },
                y = centerVerticallyTo { parent.centerY() }
            )
        }
    }

    fun setButtonsEnabled(enabled: Boolean) {
        nextButton.isEnabled = enabled
        popButton.isEnabled = enabled
        upButton.isEnabled = enabled
    }

    private fun Button.applyButtonBarStyle(): Button {
        layoutParams = LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT).apply {
            weight = 1f
        }
        background = context.getSelectableItemBackground()
        setPadding(8.dip)
        setTextAppearance(R.style.TextAppearance_AppCompat_Widget_Button_Borderless_Colored)
        return this
    }

    sealed class NavEvents {
        object PopToRoot: NavEvents()
        object Up: NavEvents()
        class Next(val index: Int, val displayUpMode: DisplayUpMode): NavEvents()
    }
}