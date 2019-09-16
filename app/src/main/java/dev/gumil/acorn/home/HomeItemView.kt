package dev.gumil.acorn.home

import android.content.Context
import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.squareup.contour.ContourLayout
import dev.gumil.acorn.R
import dev.gumil.acorn.util.getSelectableItemBackground

internal class HomeItemView(
    context: Context
) : ContourLayout(context) {

    private val imageDot = ImageView(context).apply {
        setImageResource(R.drawable.circle)
        applyLayout(
            x = leftTo { parent.left() + 24.dip },
            y = topTo { parent.top() + 24.dip }
        )
    }

    private val title = TextView(context).apply {
        setTextAppearance(android.R.style.TextAppearance_Large)
        applyLayout(
            x = leftTo { imageDot.right() + 16.dip },
            y = centerVerticallyTo { parent.centerY() }
        )
    }
    
    init {
        contourHeightOf { imageDot.bottom() + 24.dip }
        clipChildren = false
        clipToPadding = false
        background = context.getSelectableItemBackground()
    }

    fun bind(position: Int, demoModel: DemoModel) {
        val color = ContextCompat.getColor(context, demoModel.color)
        imageDot.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        title.text = demoModel.title
    }
}
