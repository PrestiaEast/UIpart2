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
import com.example.uipart2.models.Album

class AddNewAlbum : AppCompatActivity() {
    lateinit var addSubmitButton: Button
    lateinit var albumTitle: EditText
    lateinit var albumReleaseDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_album)

        val databaseHandler = AlbumsHandler(this)
        albumTitle = findViewById(R.id.add_album_title)
        albumReleaseDate = findViewById(R.id.release_date)

        addSubmitButton = findViewById(R.id.Submit)
        addSubmitButton.setOnClickListener {
            val title = albumTitle.text.toString()
            val releaseDate = albumReleaseDate.text.toString().toDouble()

            val album = Album(title = title, releaseDate = releaseDate)

            databaseHandler.create(album)
            if(databaseHandler.create(album)){
                Toast.makeText(applicationContext, "Album Added.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Something Went Wrong.", Toast.LENGTH_LONG).show()
            }
            clearfields()
        }
    }

    fun clearfields(){
        albumTitle.text.clear()
        albumReleaseDate.text.clear()
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
            R.id.display_added_album -> {
                startActivity(Intent(this, DisplayAddedAlbum::class.java))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }
}