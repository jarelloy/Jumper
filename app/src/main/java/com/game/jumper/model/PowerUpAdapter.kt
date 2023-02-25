package com.game.jumper.model
/*************************************************************************
    \file   PowerUpAdapter.kt
    \author Chua Yip Xuan, 2001488
    \date   Feb 24, 2023
    \brief  This file consist of a class for PowerUpAdapter
 *************************************************************************/

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.game.jumper.R
import com.game.jumper.database.entity.PowerUp

/*************************************************************************
 *   /brief  A class for PowerUpAdapter
 *************************************************************************/
class PowerUpAdapter : RecyclerView.Adapter<PowerUpAdapter.MyViewHolder>(){

    private var powerUpList = emptyList<PowerUp>()
    private var listener: PowerUpAdapter.OnItemClickListener? = null
    /*************************************************************************
     *   /brief  This is a inner class ViewHolder for the PowerUpAdapter
     *************************************************************************/
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var imgCosmetic : ImageView = itemView.findViewById(R.id.cosmeticImage)
        var txtViewCosmeticDesc : TextView = itemView.findViewById(R.id.cosmeticDescription)
    }
    /*************************************************************************
     *   /brief  This function overrides the onCreateViewHolder and returns
     *          the ViewHolder
     *************************************************************************/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return PowerUpAdapter.MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.customize_player_recycler_view, parent, false)
        )
    }
    /*************************************************************************
     *   /brief  This function overrides the getItemCount function and
     *          returns the item count of the size of items that will be on
     *          the recycler view
     *************************************************************************/
    override fun getItemCount(): Int {
        return powerUpList.size
    }
    /*************************************************************************
     *   /brief  This function overrides the onBindViewHolder function
     *          and binds values to the viewholder
     *************************************************************************/
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currItem = powerUpList[position]
        holder.imgCosmetic.setImageResource(currItem.image)
        holder.txtViewCosmeticDesc.text = currItem.description

        holder.txtViewCosmeticDesc.setOnClickListener {
            listener?.onItemClick(position)
        }

        holder.imgCosmetic.setOnClickListener {
            listener?.onItemClick(position)
        }
    }
    /*************************************************************************
     *   /brief  This function is called to set the data and notify the changes
     *************************************************************************/
    fun setData(powerUps : List<PowerUp>)
    {
        this.powerUpList = powerUps
        notifyDataSetChanged()
    }
    /*************************************************************************
     *   /brief  This is an interface called OnItemClickListener which
     *          calls another function to check which item has been clicked
     *************************************************************************/
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    /*************************************************************************
     *   /brief  This function sets the listener to the listener in this class
     *************************************************************************/
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}