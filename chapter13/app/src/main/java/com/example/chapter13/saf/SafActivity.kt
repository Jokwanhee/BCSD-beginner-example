package com.example.chapter13.saf

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.chapter13.R
import com.example.chapter13.databinding.ActivityMainBinding

class SafActivity:AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val READ_REQUEST_CODE = 42
    val saf = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
        if (it.resultCode == RESULT_OK) {
            dumpImageMetaData(it.data?.data!!)
            binding.imageView.setImageURI(it.data?.data!!)
        }
        Log.d("로그", "data : ${it.data}")
        Log.d("로그", "uri : ${it.data?.data!!}")
        Log.d("로그", "flag : ${it.data?.flags!!}")
        Log.d("로그", "resultCode : ${it.resultCode}")
    }
//    val saf = registerForActivityResult(ActivityResultContracts.GetContent()) {}
//    val saf = registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }

        saf.launch(intent) // StartActivityForResult
//        saf.launch("image/*") // GetContent & GetMultipleContents
    }

    fun dumpImageMetaData(uri: Uri) {
        val proj = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.SIZE
        )
        val cursor: Cursor? = contentResolver.query(uri, proj, null, null, null)
        while(cursor?.moveToNext() == true) {
            val id = cursor.getString(0)
            val displayName = cursor.getString(1)
            val dateTaken = cursor.getString(2)
            val size = cursor.getString(3)

            Log.d("로그", "id : $id")
            Log.d("로그", "displayName : $displayName")
            Log.d("로그", "dateTaken : $dateTaken")
            Log.d("로그", "size : $size")
        }

    }
}