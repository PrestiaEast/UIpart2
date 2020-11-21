package com.example.uipart2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class AlbumListDetail : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list_detail)
        val viewName = findViewById<TextView>(R.id.albumTitleTextView)
        val viewImage = findViewById<ImageView>(R.id.albumImageView)

        var albumSongs :Array<String> =arrayOf()
        val position = intent.extras!!.getString("position")

        if (position.equals("Album 1")){
            viewName.text = position
            viewImage.setImageResource(R.drawable.avicii)
            albumSongs = arrayOf("Wake Me Up", "Waiting For Love", "Levels", "The Nights", "Lay Me Down")
        }
        else if (position.equals("Album 2")){
            viewName.text = position
            viewImage.setImageResource(R.drawable.hale)
            albumSongs = arrayOf("The Day You Said Goodnight", "Kahit Pa", "Kung Wala Ka", "Chasing Cars")
        }
        else if (position.equals("Album 3")){
            viewName.text = position
            viewImage.setImageResource(R.drawable.boyce_ave)
            albumSongs = arrayOf("Someone You Loved", "Too Good At Goodbyes", "Dancing With A Stranger", "Find Me",
                    "My Girl")
        }
        var adapter = ArrayAdapter(this , android.R.layout.simple_list_item_1 , albumSongs)
        var albumDetailListView = findViewById<ListView>(R.id.albumDetailListView)
        albumDetailListView.adapter = adapter
    }
}
