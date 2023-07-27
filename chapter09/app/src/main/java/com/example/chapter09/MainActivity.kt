package com.example.chapter09

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter09.adapter.MusicAdapter
import com.example.chapter09.databinding.ActivityMainBinding
import com.example.chapter09.model.Music
import com.example.chapter09.utils.getMusicUri

class MainActivity : AppCompatActivity(), MusicAdapter.ItemOnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    private val requestReadPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ perm ->
        if (perm){
            /** Audio Permission Granted */
            adaptRecyclerView()
            binding.settingTextView.visibility = View.INVISIBLE
        } else {
            /** Audio Permission Denied */
            Toast.makeText(this, "오디오 권한을 허용하시오.", Toast.LENGTH_SHORT).show()
            binding.settingTextView.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingTextView.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
            }
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        checkPermission()
    }

    private fun checkPermission(){
        if (ActivityCompat.checkSelfPermission(
                this, READ_MEDIA_PERMISSION
        ) != PackageManager.PERMISSION_GRANTED){
            requestReadPermission.launch(READ_MEDIA_PERMISSION)
        } else {
            adaptRecyclerView()
            binding.settingTextView.visibility = View.INVISIBLE
        }
    }


    /** RecyclerView 적용 */
    private fun adaptRecyclerView(){
        val musicAdapter = MusicAdapter(getMusicList(), this)
        binding.mainRecyclerViewContainer.adapter = musicAdapter
        binding.mainRecyclerViewContainer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    /** 노래 정보 가져오기 */
    private fun getMusicList() : List<Music> {
        /** 음원 정보 주소 uri */
        val listUrl = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        /** 음원 정보 Column */
        val proj = arrayOf(
            MediaStore.Audio.Media._ID, // 고유 값
            MediaStore.Audio.Media.TITLE, // 노래 제목
            MediaStore.Audio.Media.ARTIST, // 아티스트(가수)
            MediaStore.Audio.Media.ALBUM_ID, // 앨범 아이디
            MediaStore.Audio.Media.DURATION // 노래 시간
        )

        /** ContentResolver query 에 주소(uri)와 컬럼(projection)을 입력하여 cursor 형태로 반환 */
        val cursor = contentResolver.query(listUrl, proj, null, null, null)
        val musicList = mutableListOf<Music>()
        while (cursor?.moveToNext() == true) {
            val id = cursor.getString(0)
            val title = cursor.getString(1)
            val artist = cursor.getString(2)
            val albumId = cursor.getString(3)
            val duration = cursor.getLong(4)
            Log.d("로그", "cursor:0 -> ${cursor.getString(0)}")
            Log.d("로그", "cursor:1 -> ${cursor.getString(1)}")
            Log.d("로그", "cursor:2 -> ${cursor.getString(2)}")
            Log.d("로그", "cursor:3 -> ${cursor.getString(3)}")
            Log.d("로그", "cursor:4 -> ${cursor.getString(4)}")


            val music = Music(id, title, artist, albumId, duration)
            musicList.add(music)
        }
        return musicList
    }

    override fun onStartMusic(music: Music) {
        if (mediaPlayer != null){
            mediaPlayer?.release()
            mediaPlayer = null
        }
        Log.d("로그", "onStartMusic $mediaPlayer / music : $music  ${getMusicUri(music.id)}" )
        mediaPlayer = MediaPlayer.create(baseContext, getMusicUri(music.id))
        mediaPlayer?.start()
    }

    override fun onPauseMusic(music: Music) {
        mediaPlayer?.pause()
        Log.d("로그", "onPauseMusic $mediaPlayer / music : $music")
    }

    override fun onStopMusic(music: Music) {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        Log.d("로그", "onStopMusic $mediaPlayer / music : $music")
    }

    companion object {
        /** API 33 이상 - READ_PERMISSION => READ_MEDIA_AUDIO */
        const val READ_MEDIA_PERMISSION = Manifest.permission.READ_MEDIA_AUDIO
    }
}