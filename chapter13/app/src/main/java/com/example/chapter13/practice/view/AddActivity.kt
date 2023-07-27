package com.example.chapter13.practice.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.chapter13.R
import com.example.chapter13.databinding.ActivityAddBinding
import com.example.chapter13.practice.base.BaseActivity
import com.example.chapter13.practice.db.Word
import com.example.chapter13.practice.utils.WordObject
import com.example.chapter13.practice.viewmodel.WordViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddActivity : BaseActivity<ActivityAddBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_add
    private val viewModel: WordViewModel by viewModels()
    private var originWord: Word? = null
    private var imageUri: String? = null

    private val loadImageResult = registerForActivityResult(ActivityResultContracts.GetContent()) {
//        imageUri = it.data?.data.toString()
//        binding.imageView.setImageURI(it.data?.data)
        imageUri = it.toString()
        Glide.with(this)
            .load(imageUri)
            .into(binding.imageView)
//        binding.imageView.setImageURI(it)
    }
    private val readExternalRequest = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if (it) {
            loadImageItem()
        }
    }

    override fun initView() {
        binding.view = this

        originWord = intent?.getParcelableExtra(WordObject.WORD)
        if (originWord != null){
            imageUri = originWord?.image.toString()
            originWord.let { word ->
                binding.textInputEditText.setText(word?.text)
                binding.meanTextInputEditText.setText(word?.mean)
                Glide.with(this)
                    .load(imageUri)
                    .into(binding.imageView)
//                binding.imageView.setImageURI(Uri.parse(word?.image))
            }
        }
    }

    override fun subscribe() {}
    override fun initEvent() {}


    fun loadImage() {
//        checkPermission()
        loadImageItem()
    }
//    private fun checkPermission() {
//        when {
//            ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.READ_EXTERNAL_STORAGE
//            ) == PackageManager.PERMISSION_GRANTED -> {
//                loadImageItem()
//            }
//
//            shouldShowRequestPermissionRationale(
//                Manifest.permission.READ_EXTERNAL_STORAGE
//            ) -> {
//            }
//
//            else -> {
//                readExternalRequest.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//            }
//        }
//    }

    fun loadImageItem() {
//        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
//            addCategory(Intent.CATEGORY_OPENABLE)
//            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            type = "image/*"
//        }
//        loadImageResult.launch(intent)
        loadImageResult.launch("image/*")
    }

    fun addWord() {
        when (intent.getStringExtra(WordObject.FLAG)) {
            WordObject.ADD -> {
                val word = Word(
                    binding.textInputEditText.text.toString(),
                    binding.meanTextInputEditText.text.toString(),
                    imageUri.toString()
                )
                viewModel.insert(word)
                setResult(RESULT_OK, Intent().putExtra(WordObject.ADD_WORD, word))
            }
            WordObject.UPDATE -> {
                val word = originWord?.copy(
                    text = binding.textInputEditText.text.toString(),
                    mean = binding.meanTextInputEditText.text.toString(),
                    image = imageUri.toString()
                )
                setResult(RESULT_OK, Intent().putExtra(WordObject.UPDATE_WORD, word))
            }
        }
        finish()
    }
}