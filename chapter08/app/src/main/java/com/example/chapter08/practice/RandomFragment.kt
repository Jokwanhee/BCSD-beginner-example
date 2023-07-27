package com.example.chapter08.practice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.chapter08.databinding.FragmentRandomBinding
import java.util.concurrent.ThreadLocalRandom

class RandomFragment : Fragment() {
    private lateinit var binding: FragmentRandomBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val count = requireArguments().getInt("count", 0)
        var randomCount = 0
        if (count != 0) {
            randomCount = ThreadLocalRandom.current().nextInt(1, count)
        }

        initUi(randomCount, count)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val randomFragment = parentFragmentManager.findFragmentByTag("RANDOM_TAG")
                    if (randomFragment != null ){
                        parentFragmentManager.beginTransaction()
                            .remove(randomFragment)
                            .commit()
                    }
                }
            })
    }

    private fun initUi(randNum: Int, count:Int) {
        binding.randomContentTextView.text = "Here is a random number between 0 and $count"
        binding.numberTextView.text = randNum.toString()
    }

    companion object {
        fun newInstance(): RandomFragment {
            val args = Bundle()

            val fragment = RandomFragment()
            fragment.arguments = args
            return fragment
        }
    }
}