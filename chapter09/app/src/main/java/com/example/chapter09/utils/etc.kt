package com.example.chapter09.utils

import android.net.Uri
import android.provider.MediaStore
import java.text.SimpleDateFormat

fun getMusicUri(id:String): Uri {
    return Uri.withAppendedPath(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id
    )
}

fun getAlbumUri(albumId: String?): Uri {
    return Uri.parse(
        "content://media/external/audio/albumart/$albumId"
    )
}

fun setSimpleDateFormat(duration: Long?): String = SimpleDateFormat("mm:ss").format(duration)