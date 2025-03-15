package com.example.twaran25.leaderboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.R
import com.example.twaran25.data.models.LeaderboardEntry

class LeaderboardAdapter : RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

    private val leaderboardList = mutableListOf<LeaderboardEntry>()

    fun updateData(newList: List<LeaderboardEntry>) {
        leaderboardList.clear()
        leaderboardList.addAll(newList.sortedByDescending { it.points }) // Sort before adding
        notifyDataSetChanged()
    }

    private val mappedEntries = mapOf(
        "IIIT Gwalior" to R.drawable.iiitgwalior,
        "IIIT Una" to R.drawable.iiituna,
        "IIIT Kota" to R.drawable.iiitkota,
        "IIIT Pune" to R.drawable.iiitpune,
        "IIIT Surat" to R.drawable.iiitsurat,
        "IIIT Kalyani" to R.drawable.iiit_kalyani,
        "IIIT Agartala" to R.drawable.iiitagartala,
        "IIIT Allahabad" to R.drawable.iiitallahabad,
        "IIIT Bhagalpur" to R.drawable.iiitbhagalpur,
        "IIIT Bhopal" to R.drawable.iiitbhopal,
        "IIIT Dharwad" to R.drawable.iiitdharwad,
        "IIIT Guwahati" to R.drawable.iiitguwahati,
        "IIIT Hyderabad" to R.drawable.iiithyderabad,
        "IIIT Jabalpur" to R.drawable.iiitjabalpur,
        "IIIT Kancheepuram" to R.drawable.iiitkancheepuram,
        "IIIT Kottayam" to R.drawable.iiitkottatam,
        "IIIT Lucknow" to R.drawable.iiitlucknow,
        "IIIT Manipur" to R.drawable.iiitmanipur,
        "IIIT Nagpur" to R.drawable.iiitnagpur,
        "IIIT Raichur" to R.drawable.iiitraichur,
        "IIIT Ranchi" to R.drawable.iiitranchi,
        "IIIT Sonepat" to R.drawable.iiitsonepat,
        "IIIT Tiruchirappalli" to R.drawable.iiittiruchirappalli,
        "IIIT Vadodara" to R.drawable.iiitvadodra
    )

    private fun getImageResource(collegeName: String): Int {
        return mappedEntries[collegeName] ?: R.drawable.iiitgwalior
    }

    class LeaderboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.name)
        val goldText: TextView = itemView.findViewById(R.id.gold_amount)
        val silverText: TextView = itemView.findViewById(R.id.silver_amount)
        val bronzeText: TextView = itemView.findViewById(R.id.brnz_amount)
        val positionText: TextView = itemView.findViewById(R.id.position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_info_design, parent, false)
        return LeaderboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        val entry = leaderboardList[position]

        holder.name.text = entry.collegeName
        holder.imageView.setImageResource(getImageResource(entry.collegeName))
        holder.goldText.text = entry.goldCount.toString()
        holder.silverText.text = entry.silverCount.toString()
        holder.bronzeText.text = entry.bronzeCount.toString()
        holder.positionText.text = (position + 1).toString()
    }

    override fun getItemCount(): Int = leaderboardList.size
}
