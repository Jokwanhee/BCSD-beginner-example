package com.example.chapter07.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.chapter07.R
import com.example.chapter07.databinding.ActivityNavigationDrawerBinding
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener

class NavigationDrawerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationDrawerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        /** 서랍을 열고 닫는 수신기 역할  */
//        val toggle = ActionBarDrawerToggle(
//            this,
//            binding.drawerLayout,
//            // toolbar
//            R.string.menu_alphabet, R.string.menu_number
//        )
//        /** addDrawerListener() 해당 리스너는 서랍 이벤트 알림을 유지하는 데 사용 */
//        binding.drawerLayout.addDrawerListener(toggle)
//        /** 아이콘의 상태를 동기화한다. 서랍이 닫혀있거나 열려있을 때, 햄버거 아이콘 또는 뒤로가기 아이콘 */
//        toggle.syncState()

        /** item 디자인 보기 */
        binding.navView.setNavigationItemSelectedListener { item ->
            Log.d("로그", "$item")
            true
        }
    }
}