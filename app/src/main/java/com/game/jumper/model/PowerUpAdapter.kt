package com.game.jumper.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.game.jumper.R
import com.game.jumper.database.entity.PowerUp

class PowerUpAdapter : RecyclerView.Adapter<PowerUpAdapter.MyViewHolder>(){

    private var powerUpList = emptyList<PowerUp>()
    private var listener: PowerUpAdapter.OnItemClickListener? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var imgCosmetic : ImageView = itemView.findViewById(R.id.cosmeticImage)
        var txtViewCosmeticDesc : TextView = itemView.findViewById(R.id.cosmeticDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return PowerUpAdapter.MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.customize_player_recycler_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return powerUpList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currItem = powerUpList[position]
        holder.imgCosmetic.setImageResource(currItem.image)
        holder.txtViewCosmeticDesc.text = currItem.description

        holder.txtViewCosmeticDesc.setOnClickListener {
            listener?.onItemClick(position)
        }
    }

    fun setData(powerUps : List<PowerUp>)
    {
        this.powerUpList = powerUps
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}