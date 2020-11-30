package com.example.uipart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import androidx.recyclerview.widget.RecyclerView
import com.example.uipart2.models.Album
import android.widget.Adapter as Adpter1

class albumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        var names = arrayOf("Album 1" , "Album 2" , "Album 3")
        var images = intArrayOf(R.drawable.avicii , R.drawable.hale , R.drawable.boyce_ave)

        val gridView = findViewById<GridView>(R.id.gridView)
        val mainAdapter = MainAdapter(this , names , images)
        gridView.adapter = mainAdapter
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, id ->
            val intent = Intent(this, AlbumListDetail::class.java)
            intent.putExtra("position", names[position])
            startActivity(intent)
        }
    }



}