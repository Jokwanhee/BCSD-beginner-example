package com.example.chapter10.practice

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter10.R
import com.example.chapter10.databinding.ActivityPracticeBinding
import com.example.chapter10.practice.adapter.PracticeAdapter
import com.example.chapter10.practice.model.MusicItem
import com.example.chapter10.practice.service.PracticeService
import com.example.chapter10.practice.utils.Constants
import java.text.SimpleDateFormat

class PracticeActivity : BaseActivity<ActivityPracticeBinding>(), PracticeAdapter.ItemOnClickListener {
    override val layoutId: Int
        get() = R.layout.activity_practice

    override fun initView() {
    }

    override fun initEvent() {
        checkPermission()
    }

    /** single permission request */
    private val requestReadPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ perm ->
        if (perm){
            /** Audio Permission Granted */
            adaptRecyclerView()
        } else {
            /** Audio Permission Denied */
        }
    }

    /** multi permission request */
    private val requestMultiPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){ perm ->
        when {
            perm.getOrDefault(Manifest.permission.READ_MEDIA_AUDIO, false) -> {
                adaptRecyclerView()
            }
            perm.getOrDefault(Manifest.permission.POST_NOTIFICATIONS, false) -> {}
        }
    }


    private fun checkPermission(){
        if (isPermissionGranted(Manifest.permission.READ_MEDIA_AUDIO) || isPermissionGranted(Manifest.permission.POST_NOTIFICATIONS)){
            adaptRecyclerView()
        }
        else {
//            requestReadPermission.launch(Manifest.permission.READ_MEDIA_AUDIO)
            requestMultiPermission.launch(arrayOf(
                Manifest.permission.READ_MEDIA_AUDIO,
                Manifest.permission.POST_NOTIFICATIONS
                )
            )
        }
    }

    private fun adaptRecyclerView(){
        val practiceAdapter = PracticeAdapter(getMusicList(), this)
        with(binding.containerMusicRecyclerView){
            adapter = practiceAdapter
            layoutManager =
                LinearLayoutManager(this@PracticeActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getMusicList(): List<MusicItem>{
        val listUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val proj = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION
        )

        val cursor = contentResolver.query(listUri, proj, null, null, null)
        val musicList = mutableListOf<MusicItem>()
        while (cursor?.moveToNext() == true){
            val id = cursor.getString(0)
            val title = cursor.getString(1)
            val duration = cursor.getLong(2)

            val musicItem = MusicItem(id, title, duration)
            musicList.add(musicItem)
        }
        return musicList
    }

    override fun onStartMusic(music: MusicItem) {
        startService(createServiceIntent(Constants.MEDIA_PLAY, music.musicId))
    }

    override fun onPauseMusic(music: MusicItem) {
        startService(createServiceIntent(Constants.MEDIA_PAUSE, null))
    }

    override fun onStopMusic(music: MusicItem) {
        startService(createServiceIntent(Constants.MEDIA_STOP, null))
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, PracticeService::class.java))
    }

}