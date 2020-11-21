package com.example.uipart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class QueuedSongs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, selectedSong)
        val queueListView = findViewById<ListView>(R.id.queueListView)
        queueListView.adapter = adapter
    }
}