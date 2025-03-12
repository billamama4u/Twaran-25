package com.example.twaran25.games

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.DataSource
import com.example.twaran25.DataSource.mensSports
import com.example.twaran25.DataSource.womenSports
import com.example.twaran25.R
import com.example.twaran25.contacts.ContactActivity
import com.example.twaran25.databinding.ActivitySportsBinding
import com.example.twaran25.events.GameEventsActivity
import com.example.twaran25.leaderboard.LeaderBoard

class Sports : AppCompatActivity() {



    private lateinit var recyclerView: RecyclerView
    private lateinit var filterText: TextView
    lateinit var binding: ActivitySportsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        val filterButton = findViewById<ImageButton>(R.id.filter)
        filterText = findViewById(R.id.filter_text)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        updateSportsList(DataSource.mensSports) // Default: Show Men's Sports

        filterButton.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view).apply {
                menuInflater.inflate(R.menu.filter_menu, menu)
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.filter_men -> updateSportsList(mensSports, "Men")
                        R.id.filter_women -> updateSportsList(womenSports, "Women")
                    }
                    true
                }
            }
            popupMenu.show()
        }

        binding.btnContact.setOnClickListener{
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }
        binding.btnEvents.setOnClickListener{
            val intent = Intent(this, GameEventsActivity::class.java)
            startActivity(intent)
        }
        binding.btnLeaderboard.setOnClickListener{
            val intent = Intent(this, LeaderBoard::class.java)
            startActivity(intent)
    }
        binding.btnMatches.setOnClickListener{
            val intent = Intent(this, Sports::class.java)
            startActivity(intent)
        }
    }

    private fun updateSportsList(sportsList: List<Game>, category: String = "Men") {
        filterText.text = category
        recyclerView.adapter = GameAdapter(sportsList) {
            val intent=Intent(this,LeaderBoard::class.java)
            this.startActivity(intent)
        }
    }
}
