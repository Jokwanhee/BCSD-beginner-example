package com.bcsd.chapter05.practice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.bcsd.chapter05.R
import com.bcsd.chapter05.databinding.FragmentPracticeBinding
import java.util.concurrent.ThreadLocalRandom
import kotlin.concurrent.thread

class PracticeFragment:Fragment() {
    private lateinit var binding: FragmentPracticeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPracticeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val count = requireArguments().getInt("count", 0)
        val cnt = ThreadLocalRandom.current().nextInt(1, count)
        onBackPressed()


        binding.textViewNextNumber.text = cnt.toString()
        binding.textViewContent.text = "${getString(R.string.random_text)} $count"
    }

    private fun onBackPressed(){

        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    val practiceFragment = parentFragmentManager.findFragmentByTag(MyObj.TAG)
                    if (practiceFragment != null){
                        parentFragmentManager.beginTransaction()
                            .remove(practiceFragment)
                            .commit()
                    }
                }
            })
    }


    companion object {
        fun newInstance(cnt: Int): PracticeFragment{
            val args = Bundle()

            val fragment = PracticeFragment()
            fragment.arguments = args
            return fragment
        }
    }

}