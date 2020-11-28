package com.example.uipart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uipart2.models.Song

class AddNewSong : AppCompatActivity() {
    lateinit var addSongButton: Button
    lateinit var songTitle: EditText
    lateinit var songArtist: EditText
    lateinit var songAlbum: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_song)

        val databaseHandler = SongsHandler(this)
        songTitle = findViewById(R.id.song_title)
        songArtist = findViewById(R.id.song_artist)
        songAlbum = findViewById(R.id.song_album)

        addSongButton = findViewById(R.id.addSong)
        addSongButton.setOnClickListener {
            //gets user input
            val title = songTitle.text.toString()
            val artist = songArtist.text.toString()
            val album = songAlbum.text.toString()
            //assign to model
            val song = Song(title = title,artist = artist,album= album)
            //save to database
            if(databaseHandler.create(song)) {
                Toast.makeText(applicationContext, "Song Added.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Something Went Wrong.", Toast.LENGTH_LONG).show()
            }
            clearfields()
        }
    }

    fun clearfields(){
        songTitle.text.clear()
        songArtist.text.clear()
        songAlbum.text.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.go_to_queue_page -> {
                val intent = Intent(this, QueuedSongs::class.java)
                startActivity(intent)
                true
            }
            R.id.go_to_songs -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.go_to_albums -> {
                startActivity(Intent(this, albumActivity::class.java))
                true
            }
            R.id.display_added_songs -> {
                startActivity(Intent(this, DisplayAddedSongs::class.java))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }
}