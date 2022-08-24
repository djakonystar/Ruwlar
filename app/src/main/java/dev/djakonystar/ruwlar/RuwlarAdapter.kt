package dev.djakonystar.ruwlar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import dev.djakonystar.ruwlar.databinding.ItemRuwBinding

class RuwlarAdapter : RecyclerView.Adapter<RuwlarAdapter.RuwlarViewHolder>() {

    var models: List<Ruw> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class RuwlarViewHolder(private val binding: ItemRuwBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ruw: Ruw) {
            binding.tvName.text = ruw.name
            binding.ivGoto.isVisible = ruw.hasChildren == 1
            binding.root.isEnabled = ruw.hasChildren == 1

            binding.root.setOnClickListener {
                onItemClick.invoke(ruw)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuwlarViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_ruw, parent, false)
        val binding = ItemRuwBinding.bind(itemView)
        return RuwlarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RuwlarViewHolder, position: Int) {
        holder.bind(models[position])
    }

    override fun getItemCount() = models.size

    private var onItemClick: (ruw: Ruw) -> Unit = {}
    fun setOnItemClickListener(onItemClick: (ruw: Ruw) -> Unit) {
        this.onItemClick = onItemClick
    }
}
