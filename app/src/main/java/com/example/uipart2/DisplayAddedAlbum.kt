package com.example.uipart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import com.example.uipart2.models.Album

class DisplayAddedAlbum : AppCompatActivity() {
    lateinit var albumsListView: ListView
    lateinit var albumHandler: AlbumsHandler
    lateinit var albums: MutableList<Album>
    lateinit var adapter: ArrayAdapter<Album>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_added_album)
        albumsListView = findViewById(R.id.albumList)

        albumHandler = AlbumsHandler(this)
        albums = albumHandler.read()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albums)
        albumsListView.adapter = adapter
        albumsListView.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, id ->
            val intent = Intent(this, DisplaySongsForNewAlbum::class.java)
            startActivity(intent)
        }

        registerForContextMenu(albumsListView)
    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        val inflater = menuInflater
        inflater.inflate(R.menu.album_item_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.edit_album -> {
                val album_id = albums[info.position].id
                val intent = Intent(applicationContext, EditAlbumActivity::class.java)
                intent.putExtra("album_id", album_id)
                startActivity(intent)
                true
            }
            R.id.add_song_to_album -> {
                val album = albums[info.position]
                startActivity(Intent(this, AddNewSong::class.java))
                return true
            }
            else -> super.onContextItemSelected(item)
        }
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
            R.id.add_song -> {
                startActivity(Intent(this, AddNewSong::class.java))
                true
            }
            R.id.add_album -> {
                startActivity(Intent(this, AddNewAlbum::class.java))
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }
}