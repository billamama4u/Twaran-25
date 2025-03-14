package com.example.twaran25.events

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twaran25.CollegeAdapter
import com.example.twaran25.DataSource.colleges
import com.example.twaran25.DataSource.events
import com.example.twaran25.Events
import com.example.twaran25.R
import com.example.twaran25.contacts.AdminContact
import com.example.twaran25.contacts.ContactActivity
import com.example.twaran25.databinding.ActivityAdminEventsBinding
import com.example.twaran25.games.AdminMatches
import com.example.twaran25.games.Sports
import com.example.twaran25.leaderboard.AdminLeaderboard
import com.example.twaran25.leaderboard.LeaderBoard

class AdminEvents : AppCompatActivity() {
    lateinit var eventsBinding: ActivityAdminEventsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        eventsBinding = ActivityAdminEventsBinding.inflate(layoutInflater)
        setContentView(eventsBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val collegeRow: RecyclerView = eventsBinding.collegeList

        collegeRow.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        collegeRow.adapter = CollegeAdapter(colleges) { collegeName ->
            //Refresh the list when a college icon is clicked
            Toast.makeText(this, "Clicked on $collegeName", Toast.LENGTH_SHORT).show()
        }

        val eventRecyclerView: RecyclerView = eventsBinding.eventsRecycler
        eventRecyclerView.adapter = AdminMatchesAdapter(events)
        eventRecyclerView.layoutManager = LinearLayoutManager(this)
        navigation()
    }

    private fun navigation(){
        eventsBinding.btnContact.setOnClickListener {
            if (javaClass.simpleName != AdminContact::class.java.simpleName) {
                val intent = Intent(this, AdminContact::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        eventsBinding.btnEvents.setOnClickListener {
            if (javaClass.simpleName != AdminEvents::class.java.simpleName) {
                val intent = Intent(this, AdminEvents::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        eventsBinding.btnLeaderboard.setOnClickListener {
            if (javaClass.simpleName != AdminLeaderboard::class.java.simpleName) {
                val intent = Intent(this, AdminLeaderboard::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        eventsBinding.btnMatches.setOnClickListener {
            if (javaClass.simpleName != AdminMatches::class.java.simpleName) {
                val intent = Intent(this, AdminMatches::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}