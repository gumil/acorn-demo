package dev.gumil.acorn.home

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.contour.ContourLayout

@SuppressLint("ViewConstructor")
internal class HomeView(
    context: Context,
    private val demoModels: List<DemoModel>,
    private val onItemClickedListener: (DemoModel) -> Unit
) : ContourLayout(context) {

    private val homeAdapter by lazy {
        HomeAdapter(demoModels).apply {
            onItemClicked = ::onItemClicked
        }
    }

    private val recyclerView = RecyclerView(context).apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        adapter = homeAdapter
        applyLayout(
            x = leftTo { parent.left() }
                .rightTo { parent.right() },
            y = topTo { parent.top() }
        )
    }

    private fun onItemClicked(position: Int, demoModel: DemoModel) {
        onItemClickedListener(demoModel)
    }
}
