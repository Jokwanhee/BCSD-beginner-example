package com.example.chapter06.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.chapter06.databinding.DialogFragmentCustomBinding
import com.example.chapter06.ext.setWidthPercent

class CustomDialog: DialogFragment() {
    private lateinit var binding: DialogFragmentCustomBinding

    interface OnEditNameTextListener{
        fun onChangedName(name:String)
    }

    lateinit var listener: OnEditNameTextListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentCustomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(85)
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        binding.buttonOk.setOnClickListener {
            if (binding.editTextEditName.text.isNotEmpty()){
//                viewModel.setEditName(binding.editTextEditName.text.toString())
                listener = context as OnEditNameTextListener
                listener.onChangedName(binding.editTextEditName.text.toString())
                dismiss()
            }
        }
    }

    companion object {
        var position:Int? = null
        fun newInstance(pos:Int): CustomDialog{
            val args = Bundle()
            position = pos
            val fragment = CustomDialog()
            fragment.arguments = args
            return fragment
        }
    }
}