package com.example.uipart2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcel
import android.os.Parcelable
import com.example.uipart2.models.Song
import com.example.uipart2.models.SongsInAlbum

class SongsAddedToAlbumTableHandler(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "songs_to_album_database"
        private val TABLE_NAME = "added_songs"
        private val COL_ID = "id"
        private val COL_TITLE = "title"
        private val COL_ARTIST = "artist"
    }

    override fun onCreate(db: SQLiteDatabase?) {
//        TODO("Not yet implemented")
        val query = "CREATE TABLE "+ TABLE_NAME +" ("+COL_ID +" INTEGER PRIMARY KEY, "+COL_TITLE +" TEXT, "+ COL_ARTIST +" TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        TODO("Not yet implemented")
        db!!.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }

    fun create(songInAlbum: SongsInAlbum): Boolean{
        //get the database (readableDatabase)
        val database = this.writableDatabase
        // set ContentValues
        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, songInAlbum.title)
        contentValues.put(COL_ARTIST, songInAlbum.artist)
        // insert
        val result = database.insert(TABLE_NAME, null, contentValues)
        // check results
        if(result == (0).toLong()){
            return false
        }
        return true
    }

}