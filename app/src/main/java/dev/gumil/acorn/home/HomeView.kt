package dev.gumil.acorn.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.contour.ContourLayout

@SuppressLint("ViewConstructor", "ResourceType")
internal class HomeView(
    context: Context,
    demoModels: List<DemoModel>,
    onItemClickedListener: (DemoModel) -> Unit
) : ContourLayout(context) {

    init {
        id = 1001

        RecyclerView(context).apply {
            id = 1002
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = HomeAdapter(demoModels).apply {
                onItemClicked = { _, demoModel ->
                    onItemClickedListener(demoModel)
                }
            }
            applyLayout(
                x = leftTo { parent.left() }
                    .rightTo { parent.right() },
                y = topTo { parent.top() }.bottomTo { parent.bottom() }
            )
        }
    }
}
