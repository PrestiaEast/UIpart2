package com.example.uipart2.models

class SongsInAlbum (var id: Int = 0, var title: String, var artist: String){
    override fun toString(): String {
        return "Title: ${title}, Artist: ${artist}"
    }
}