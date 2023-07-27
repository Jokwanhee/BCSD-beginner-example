package com.bcsd.chapter02.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bcsd.chapter02.R

class LinearFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_linear, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): LinearFragment {
            val fragment = LinearFragment()
            val bundle = Bundle().apply {
                putBundle("from_linear", bundleOf("bundle_linear" to "linear"))
                fragment.arguments = this
            }
            return fragment
        }
    }
}