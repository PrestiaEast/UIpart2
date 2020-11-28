package com.example.uipart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uipart2.models.Song

class EditSongActivity : AppCompatActivity() {
    lateinit var addEditedSongButton: Button
    lateinit var editSongTitle: EditText
    lateinit var editSongArtist: EditText
    lateinit var editSongAlbum: EditText
    lateinit var song: Song
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_song)

        val song_id =  intent.getIntExtra("song_id", 0)
        val databaseHandler = SongsHandler(this)
        val song = databaseHandler.readOne(song_id)

        editSongTitle = findViewById(R.id.edit_song_title)
        editSongArtist = findViewById(R.id.edit_song_artist)
        editSongAlbum = findViewById(R.id.edit_song_album)
        addEditedSongButton = findViewById(R.id.addEditedSong)

        editSongTitle.setText(song.title)
        editSongArtist.setText(song.artist)
        editSongAlbum.setText(song.album)

        addEditedSongButton.setOnClickListener {
            val title = editSongTitle.text.toString()
            val artist = editSongArtist.text.toString()
            val album = editSongAlbum.text.toString()
            //assign to model
            val updatedSong = Song(id = song.id, title = title, artist = artist,album = album)
            //save to database
            if(databaseHandler.update(updatedSong)) {
                Toast.makeText(applicationContext, "Song Updated", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Something Went Wrong.", Toast.LENGTH_LONG).show()
            }

        }

    }
}