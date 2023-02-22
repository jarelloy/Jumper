package edu.singaporetech.iwsp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.game.jumper.R

class ScoreAdapter(
    private val list: List<Score>) :
    RecyclerView.Adapter<ScoreAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName : TextView = itemView.findViewById(R.id.highscoreName)
        val scoreVal : TextView = itemView.findViewById(R.id.highscoreVal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.scoreboard_recycler_view, parent, false)
        val viewHolder =  ViewHolder(adapterLayout)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.playerName.text = item.playerID
        holder.scoreVal.text = item.score.toString()

        }

    override fun getItemCount() = list.size

}