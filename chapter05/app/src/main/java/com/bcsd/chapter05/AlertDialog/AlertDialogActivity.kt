package com.bcsd.chapter05.AlertDialog

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.PopupMenu
import android.widget.PopupMenu.OnDismissListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bcsd.chapter05.R
import com.bcsd.chapter05.databinding.ActivityAlertDialogBinding

class AlertDialogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlertDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlertDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBasicDialog.setOnClickListener {
            setAlertDialog()
        }
        binding.buttonDialog.setOnClickListener {
            setButtonAlertDialog()
        }
        binding.buttonArrDialog.setOnClickListener {
            setArrayAlertDialog()
        }
        binding.buttonCustomDialog.setOnClickListener {
            setCustomAlertDialog()
        }

    }

    fun setAlertDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle("이름 알림")
            .setMessage("안녕하세요. 저는 조관희입니다.")
            .create()
            .show()
    }

    fun setButtonAlertDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("버튼 3개")
            setMessage("버튼이 3개 클릭해보자!")
            setCancelable(false)
            // 리스너를 불러올 때, local variables 가 두 개 이상을 가질 때는 명시해줘야 한다.
            setPositiveButton("확인", object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, id: Int) {
                    Toast.makeText(this@AlertDialogActivity, "확인!", Toast.LENGTH_SHORT).show()
                }
            })
            setNeutralButton("중립"){dialog, id ->
                Toast.makeText(this@AlertDialogActivity, "중립", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("취소"){dialog, id ->
                Toast.makeText(this@AlertDialogActivity, "취소!", Toast.LENGTH_SHORT).show()
            }
        }
        builder.create()
        builder.show()
    }

    fun setArrayAlertDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val bool = booleanArrayOf(true,true,false)
        var pos = 0
        builder.apply {
            setTitle("배열 3개")
            // 기본 리스트
//            setItems(R.array.list){ dialog, id ->
//            }

            // 체크박스
//            setMultiChoiceItems(R.array.list, bool, object: DialogInterface.OnMultiChoiceClickListener{
//                override fun onClick(p0: DialogInterface?, p1: Int, p2: Boolean) {
//                    bool[p1] = p2
//                }
//            })

            // 라디오 버튼
            setSingleChoiceItems(R.array.list, pos, object: DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    pos = p1
                }
            })
        }
        builder.create()
        builder.show()
    }

    fun setCustomAlertDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.apply {
            setTitle("커스텀")
            setView(R.layout.custom_alert)
        }
        builder.create()
        builder.show()
    }

}