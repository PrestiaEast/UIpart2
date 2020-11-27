package com.example.uipart2

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

var selectedSong = arrayListOf<String>()
val productsArray = arrayOf(
        "Wake Me Up",
        "The Day You Said Goodnight",
        "Waiting For Love",
        "Kahit Pa",
        "Kung Wala Ka",
        "Levels",
        "Someone You Loved",
        "Too Good At Goodbyes",
        "Dancing With A Stranger",
        "Find Me",
        "My Girl",
        "The Nights",
        "Chasing cars",
        "Lay Me Down"
)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productsArray)
        val AlbumListDetail = findViewById<ListView>(R.id.songList)
        AlbumListDetail.adapter = adapter
        registerForContextMenu(AlbumListDetail)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.long_press, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_queue -> {
                Toast.makeText(this, "Added to Queues", Toast.LENGTH_SHORT).show()
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                selectedSong.add(productsArray[info.position])
                val snackbar = Snackbar.make(mainLayout, "View Listed Songs?", Snackbar.LENGTH_LONG)
                snackbar.setAction("Go", View.OnClickListener {
                    startActivity(Intent(applicationContext, QueuedSongs::class.java))
                })
                snackbar.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

}