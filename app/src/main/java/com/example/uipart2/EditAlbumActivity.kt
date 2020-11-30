package com.example.uipart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uipart2.models.Album

class EditAlbumActivity : AppCompatActivity() {
    lateinit var editSubmitButton: Button
    lateinit var editAlbumTitle: EditText
    lateinit var editAlbumReleaseDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_album)

        val album_id = intent.getIntExtra("album_id", 0)
        val databaseHandler = AlbumsHandler(this)
        val album = databaseHandler.readOne(album_id)

        editAlbumTitle = findViewById(R.id.edit_album_title)
        editAlbumReleaseDate = findViewById(R.id.edit_release_date)
        editSubmitButton = findViewById(R.id.Submit_edited)

        editAlbumTitle.setText(album.title)
        editAlbumReleaseDate.setText(album.releaseDate.toString())

        editSubmitButton.setOnClickListener {
            val title = editAlbumTitle.text.toString()
            val releaseDate = editAlbumReleaseDate.text.toString()

            val updatedAlbum = Album(id = album.id, title = album.title, releaseDate = album.releaseDate)

            if (databaseHandler.update(updatedAlbum)) {
                Toast.makeText(applicationContext, "Album Updated", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Something Went Wrong.", Toast.LENGTH_LONG).show()
            }
        }

    }
}