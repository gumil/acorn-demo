package dev.gumil.acorn.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

internal class HomeAdapter(
    private val items: List<DemoModel>
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var onItemClicked: ((Int, DemoModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(HomeItemView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal inner class ViewHolder(private val view: HomeItemView) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int, item: DemoModel) {
            view.bind(position, item)
            view.setOnClickListener { onItemClicked?.invoke(position, item) }
        }
    }
}
