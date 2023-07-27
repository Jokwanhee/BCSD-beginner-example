package com.example.chapter09.model

import android.net.Uri
import android.provider.MediaStore
import java.text.SimpleDateFormat

/** id 제외한 4개 인자 null 값일 수 있으니 null 허용 */
data class Music(
    val id:String,
    val title: String?,
    val artist: String?,
    val albumId: String?,
    val duration: Long?
)


