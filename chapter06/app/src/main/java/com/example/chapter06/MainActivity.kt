package com.example.chapter06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter06.adapter.PracticeAdapter
import com.example.chapter06.databinding.ActivityMainBinding
import com.example.chapter06.dialog.CustomDialog

class MainActivity : AppCompatActivity(), PracticeAdapter.OnClickListener,
    CustomDialog.OnEditNameTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var practiceAdapter: PracticeAdapter
    private val nameList = mutableListOf<String>()
    var rememberPosition = 0

    private lateinit var customDialog: CustomDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("로그", "onCreate")

        practiceAdapter = PracticeAdapter(nameList, this)

        with(binding.recyclerViewContainer) {
            adapter = practiceAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }


        binding.addButton.setOnClickListener {
            if (binding.editTextInputName.text.isNotEmpty()) {
                nameList.add(binding.editTextInputName.text.toString())
                practiceAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        Log.d("로그", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("로그", "onResume")

        super.onResume()
    }

    override fun onPause() {
        Log.d("로그", "onPause")

        super.onPause()
    }

    override fun onStop() {
        Log.d("로그", "onStop")

        super.onStop()
    }

    private fun createDialog(pos: Int) {
        AlertDialog.Builder(this)
            .setTitle("이름 목록 삭제하기")
            .setMessage("이름 목록을 삭제해보자.")
            .setPositiveButton("삭제") { p0, p1 ->
                nameList.removeAt(pos)
//                practiceAdapter.notifyDataSetChanged()
                practiceAdapter.notifyItemRemoved(pos)
            }
            .setNegativeButton("취소", null)
            .create()
            .show()
    }

    private fun showDialogFragment(pos: Int) {
        customDialog = CustomDialog.newInstance(pos)
        customDialog.show(supportFragmentManager, "Custom Dialog")
    }

    override fun onChangedName(name: String) {
        nameList[rememberPosition] = name
        practiceAdapter.notifyItemChanged(rememberPosition)
//        practiceAdapter.notifyDataSetChanged()
        binding.editTextInputName.setText(null)
    }

    override fun onClick(position: Int) {
        createDialog(position)
    }


    override fun onLongClick(position: Int) {
        showDialogFragment(position)
        rememberPosition = position
    }
}