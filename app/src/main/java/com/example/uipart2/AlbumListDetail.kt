package com.example.uipart2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class AlbumListDetail : AppCompatActivity() {
    private var adapter: ArrayAdapter<String>? = null
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

        registerForContextMenu(albumDetailListView)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
    {
        super.onCreateContextMenu(menu,v,menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.album_list_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_item -> {
                val intent = intent
                val position = intent.extras!!.getInt("position")
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Remove this song from album?")
                    .setCancelable(false)
                    .setPositiveButton("Remove", DialogInterface.OnClickListener{ dialog, which ->
                        if(position == 0){
                            selectedSong.removeAt(info.position)
                        }
                        else if(position == 1){
                            selectedSong.removeAt(info.position)
                        }
                        else {
                            selectedSong.removeAt(info.position)
                        }
                        adapter?.notifyDataSetChanged()
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener{
                            dialog, which ->
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("Album Dialog")
                alert.show()
            }
        }
        return super.onContextItemSelected(item)
    }
}
