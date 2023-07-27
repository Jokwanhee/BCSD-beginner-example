package com.bcsd.chapter02.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bcsd.chapter02.R

class FrameFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_frame, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): FrameFragment {
            val fragment = FrameFragment()
            val args = Bundle().apply {
                putBundle("from_frame", bundleOf("bundle_frame" to "frame"))
                fragment.arguments = this
            }
            return fragment
        }
    }
}