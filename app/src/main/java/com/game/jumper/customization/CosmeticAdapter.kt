package edu.singaporetech.iwsp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.game.jumper.R

class CosmeticAdapter(
    private val list: List<Cosmetic>) :
    RecyclerView.Adapter<CosmeticAdapter.ViewHolder>() {
    private var listener: OnItemClickListener? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageID : ImageView = itemView.findViewById(R.id.cosmeticImage)
        val description : TextView = itemView.findViewById(R.id.cosmeticDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.customize_player_recycler_view, parent, false)
        val viewHolder =  ViewHolder(adapterLayout)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.imageID.setImageResource(currentItem.imageResource)
        holder.description.text = currentItem.description

        holder.description.setOnClickListener {
            listener?.onItemClick(position)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun getItemCount() = list.size

}