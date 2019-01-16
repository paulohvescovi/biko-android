package paulo.com.br.bico.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import paulo.com.br.bico.databinding.CategorieServiceItemBinding
import paulo.com.br.bico.entity.CategorieService
import paulo.com.br.bico.entity.CategorieServiceFull

class CategorieServiceAdapter(var items: List<CategorieServiceFull>) : RecyclerView.Adapter<CategorieServiceAdapter.ViewHolder>(), AdapterItemsContract{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CategorieServiceItemBinding = CategorieServiceItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun replaceItems(items: List<*>) {
        this.items = items as List<CategorieServiceFull>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(val binding: CategorieServiceItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(categorie: CategorieServiceFull) {
            binding.category = categorie
            binding.executePendingBindings()
        }
    }
}