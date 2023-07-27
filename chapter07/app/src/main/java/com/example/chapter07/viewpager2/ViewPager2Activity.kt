package com.example.chapter07.viewpager2

import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.chapter07.R
import com.example.chapter07.databinding.ActivityViewPager2Binding
import com.example.chapter07.viewpager2.adapter.ScreenSlidePagerAdapter
import com.example.chapter07.viewpager2.adapter.SlideRecyclerViewAdapter
import com.example.chapter07.viewpager2.animation.DepthPageTransformer
import com.example.chapter07.viewpager2.animation.ZoomOutPageTransformer
import com.example.chapter07.viewpager2.fragment.SlideFragment
import com.example.chapter07.viewpager2.model.ItemData

class ViewPager2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPager2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        /** Initialize FragmentStateAdapter adapter */
        val pagerAdapter = ScreenSlidePagerAdapter(this)

        /** Initialize RecyclerViewAdapter adapter */
        val itemList = mutableListOf<ItemData>()
        for (i in 0..100){
            itemList.add(ItemData(i, "$i 설명서 설명서 설명서"))
        }
        val pagerRecyclerViewAdapter = SlideRecyclerViewAdapter(itemList)

        /** adapter 설정 */
        binding.pager.adapter = pagerAdapter

        /** animation 적용 */
//        binding.pager.setPageTransformer(ZoomOutPageTransformer())
        binding.pager.setPageTransformer(DepthPageTransformer())

        /** onBackPressed deprecated instead of use OnBackPressedCallback
         * 새로운 콜백 객체 만들기
         * */
        val onBackPressedCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Log.d("로그", "onBackPressedCallback: handleOnBackPressed : $isEnabled")
                if (binding.pager.currentItem == 0){
                    // viewPager 위치가 처음일 때, 해당 액티비티 종료
                    finish()
                } else {
                    // viewPager RTL 로 스와이프 할 때, 1->2 로 가면 2 에서 backButton 클릭 시 1로 돌아간다.
                    binding.pager.currentItem = binding.pager.currentItem - 1
                }
            }
        }
        /** To addCallback, use onBackPressedDispatcher 뒤로 누르기 콜백 추가 */
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}
