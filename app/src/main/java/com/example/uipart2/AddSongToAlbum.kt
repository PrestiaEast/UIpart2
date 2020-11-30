package com.example.uipart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uipart2.models.SongsInAlbum

class AddSongToAlbum : AppCompatActivity() {
    lateinit var submitAdded: Button
    lateinit var addSongTitleToAlbum: EditText
    lateinit var addSongArtistToAlbum: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song_to_album)

        val databaseHandler = SongsHandler(this)
        addSongTitleToAlbum = findViewById(R.id.add_song_title_to_album)
        addSongArtistToAlbum = findViewById(R.id.add_song_artist_to_album)

        submitAdded = findViewById(R.id.Submit_added)
        submitAdded.setOnClickListener {
            val title = addSongTitleToAlbum.text.toString()
            val artist = addSongArtistToAlbum.text.toString()

            val song = SongsInAlbum(title = title,artist = artist)
            if (databaseHandler.create(song)){
                Toast.makeText(applicationContext, "Song Added to Album.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Something Went Wrong.", Toast.LENGTH_LONG).show()
            }
            clearfields()
        }
    }

    fun clearfields(){
        addSongTitleToAlbum.text.clear()
        addSongArtistToAlbum.text.clear()

    }
}
