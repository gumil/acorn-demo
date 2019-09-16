package dev.gumil.acorn.home

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.contour.ContourLayout

@SuppressLint("ViewConstructor")
internal class HomeView(
    context: Context,
    var demoModels: List<DemoModel>
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
        Toast.makeText(context, demoModel.title, Toast.LENGTH_LONG).show()
    }
}
