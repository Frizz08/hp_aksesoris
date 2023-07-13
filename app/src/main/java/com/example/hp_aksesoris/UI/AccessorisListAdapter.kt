package com.example.hp_aksesoris.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hp_aksesoris.R
import com.example.hp_aksesoris.model.Accessoris

class AccessorisListAdapter(
    private val onItemClickListener: (Accessoris) -> Unit
): ListAdapter<Accessoris, AccessorisListAdapter.AccessorisViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccessorisViewHolder {
        return AccessorisViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AccessorisViewHolder, position: Int) {
        val accessoris = getItem(position)
        holder.bind(accessoris)
        holder.itemView.setOnClickListener {
            onItemClickListener(accessoris)
        }
    }

    class AccessorisViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView= itemView.findViewById(R.id.nameTextView)
        private val addressTextView: TextView= itemView.findViewById(R.id.addressTextView)

        fun bind(accessoris: Accessoris?) {
            nameTextView.text= accessoris?.name
            addressTextView.text= accessoris?.address
        }

        companion object {
            fun create(parent: ViewGroup): AccessorisListAdapter.AccessorisViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_accessoris, parent, false)
                return AccessorisViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Accessoris>(){
            override fun areItemsTheSame(oldItem: Accessoris, newItem: Accessoris): Boolean {
                return oldItem== newItem
            }

            override fun areContentsTheSame(oldItem: Accessoris, newItem: Accessoris): Boolean {
                return oldItem.id== newItem.id
            }
        }
    }
}

private fun AccessorisListAdapter.AccessorisViewHolder.bind(accessoris: Accessoris?) {

}
