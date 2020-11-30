package com.example.uipart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.uipart2.models.Song

class DisplaySongsForNewAlbum : AppCompatActivity() {
    lateinit var songsInNewAlbumListView: ListView
    lateinit var songsHandler: SongsHandler
    lateinit var songs: MutableList<Song>
    lateinit var adapter: ArrayAdapter<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_songs_for_new_album)
        songsInNewAlbumListView = findViewById(R.id.songsList_album)

//        songsHandler = SongsAddedToAlbumTableHandler(this)


    }
}