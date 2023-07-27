package com.example.chapter07.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.chapter07.R
import com.example.chapter07.bottomnavigation.adapter.BottomNaviAdapter
import com.example.chapter07.bottomnavigation.fragment.AFragment
import com.example.chapter07.bottomnavigation.fragment.BFragment
import com.example.chapter07.bottomnavigation.fragment.CFragment
import com.example.chapter07.databinding.ActivityBottomNaviBinding

class BottomNaviActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNaviBinding
    private val aFragment by lazy { AFragment() }
    private val bFragment by lazy { BFragment() }
    private val cFragment by lazy { CFragment() }
    private lateinit var bottomNaviAdapter: BottomNaviAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setFragment(aFragment)

        /** bottom navigation view click event handle */
        binding.bottomNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.action_rainbow -> { binding.viewpager2Container.currentItem = 0 }
                R.id.action_number -> { binding.viewpager2Container.currentItem = 1 }
                R.id.action_alphabet -> { binding.viewpager2Container.currentItem = 2 }
            }
            true
        }

        /** bottomNavigation and ViewPager2 adapter
         * use FragmentStateAdapter
         * */
        bottomNaviAdapter = BottomNaviAdapter(this)
        binding.viewpager2Container.adapter = bottomNaviAdapter
        /** ViewPager swipe -> bottomNavigation change Menu Icon */
        binding.viewpager2Container.registerOnPageChangeCallback(object: OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNavi.menu.getItem(position).isChecked = true
            }
        })
    }

//    private fun setFragment(frag:Fragment){
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, frag)
//            .commit()
//    }
}