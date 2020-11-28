package com.example.uipart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.uipart2.models.Song

class DisplayAddedSongs : AppCompatActivity() {
    lateinit var songsListView: ListView
    lateinit var songsHandler: SongsHandler
    lateinit var songs: MutableList<Song>
    lateinit var adapter: ArrayAdapter<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_added_songs)
        songsListView = findViewById<ListView>(R.id.songs_added)

        //get table handler
        songsHandler = SongsHandler(this)
        // get records
        songs = songsHandler.read()
        // attach to Adapter
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, songs)
        songsListView.adapter = adapter

        registerForContextMenu(songsListView)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        val inflater = menuInflater
        inflater.inflate(R.menu.song_item_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.edit_song -> {
                val song_id = songs[info.position].id
                val intent = Intent(applicationContext, EditSongActivity::class.java)
                intent.putExtra("song_id", song_id)
                startActivity(intent)
                true
            }
            R.id.delete_song -> {
                val song = songs[info.position]
                if(songsHandler.delete(song)){
                    songs.removeAt(info.position)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(applicationContext, "Book was Deleted.", Toast.LENGTH_SHORT)
                } else{
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT)
                }
                true
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
            else -> {
                super.onOptionsItemSelected(item)
            }
        }



    }
}