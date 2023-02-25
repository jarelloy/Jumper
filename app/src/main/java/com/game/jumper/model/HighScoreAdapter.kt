package com.game.jumper.model

/*************************************************************************
    \file   HighScoreAdapter.kt
    \author Chua Yip Xuan, 2001488
    \date   Feb 24, 2023
    \brief  This file consist of a class for HighScoreAdapter
 *************************************************************************/
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.game.jumper.R
import com.game.jumper.database.entity.HighScore

/*************************************************************************
 *   /brief  A class for HighScoreAdapter
 *************************************************************************/
class HighScoreAdapter : RecyclerView.Adapter<HighScoreAdapter.MyViewHolder>(){

    private var highScoreList = emptyList<HighScore>()

    /*************************************************************************
     *   /brief  This is a inner class ViewHolder for the HighScoreAdapter
     *************************************************************************/
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        //val imgCosmetic : ImageView = itemView.findViewById(R.id.cosmeticImage)
        val txtViewHSName : TextView = itemView.findViewById(R.id.highscoreName)
        val txtViewHSValue : TextView = itemView.findViewById(R.id.highscoreVal)
    }
    /*************************************************************************
     *   /brief  This function overrides the onCreateViewHolder and returns
     *          the ViewHolder
     *************************************************************************/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.scoreboard_recycler_view, parent, false))
    }
    /*************************************************************************
     *   /brief  This function overrides the getItemCount function and
     *          returns the item count of the size of items that will be on
     *          the recycler view
     *************************************************************************/
    override fun getItemCount(): Int {
        return highScoreList.size
    }
    /*************************************************************************
     *   /brief  This function overrides the onBindViewHolder function
     *          and binds values to the viewholder
     *************************************************************************/
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currItem = highScoreList[position]
        holder.txtViewHSName.text = currItem.userId
        holder.txtViewHSValue.text = currItem.score.toString()
    }
    /*************************************************************************
     *   /brief  This function is called to set the data and notify the changes
     *************************************************************************/
    fun setData(highScores : List<HighScore>)
    {
        this.highScoreList = highScores
        notifyDataSetChanged()
    }
}