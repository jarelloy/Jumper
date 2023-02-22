package com.game.jumper.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.game.jumper.R
import com.game.jumper.database.entity.HighScore

class HighScoreAdapter : RecyclerView.Adapter<HighScoreAdapter.MyViewHolder>(){

    private var highScoreList = emptyList<HighScore>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        //val imgCosmetic : ImageView = itemView.findViewById(R.id.cosmeticImage)
        val txtViewHSName : TextView = itemView.findViewById(R.id.highscoreName)
        val txtViewHSValue : TextView = itemView.findViewById(R.id.highscoreVal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.scoreboard_recycler_view, parent, false))
    }

    override fun getItemCount(): Int {
        return highScoreList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currItem = highScoreList[position]
        holder.txtViewHSName.text = currItem.userId
        holder.txtViewHSValue.text = currItem.score.toString()
    }

    fun setData(highScores : List<HighScore>)
    {
        this.highScoreList = highScores
        notifyDataSetChanged()
    }
}