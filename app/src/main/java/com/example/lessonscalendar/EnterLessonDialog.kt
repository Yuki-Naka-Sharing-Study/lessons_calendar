package com.example.lessonscalendar

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.lessonscalendar.databinding.EnterLessonDialogBinding

class EnterLessonDialog : DialogFragment() {
    private var title: String = ""
    private var message: String = ""
    private var positiveButtonText: String = ""

    companion object {
        private const val DEFAULT_POSITIVE_BUTTON_TEXT = "OK"
        private const val TITLE_KEY = "TitleKey"
        private const val MESSAGE_KEY = "MessageKey"
        private const val REQUEST_POSITIVE_BUTTON_KEY = "RequestPositiveButtonKey"
        private const val POSITIVE_BUTTON_TEXT_KEY = "PositiveButtonTextKey"
    }

    class Builder(private val fragment: Fragment) {
        private val bundle = Bundle()

        fun setTitle(title: String): Builder {
            return this.apply {
                bundle.putString(TITLE_KEY, title)
            }
        }

        fun setMessage(message: String): Builder {
            return this.apply {
                bundle.putString(MESSAGE_KEY, message)
            }
        }

        fun setPositiveButton(buttonText: String, listener: (() -> Unit)? = null): Builder {
            fragment.childFragmentManager
                .setFragmentResultListener(
                    REQUEST_POSITIVE_BUTTON_KEY,
                    fragment.viewLifecycleOwner
                ) { _, _ ->
                    listener?.invoke()
                }
            return this.apply {
                bundle.putString(POSITIVE_BUTTON_TEXT_KEY, buttonText)
            }
        }

        fun build(): EnterLessonDialog {
            return EnterLessonDialog().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        // ダイアログの背景を透過にする
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        arguments?.let {
            title = it.getString(TITLE_KEY, "")
            message = it.getString(MESSAGE_KEY, "")
            positiveButtonText =
                it.getString(POSITIVE_BUTTON_TEXT_KEY, DEFAULT_POSITIVE_BUTTON_TEXT)
        }
        val binding = EnterLessonDialogBinding.inflate(requireActivity().layoutInflater)
        binding.title.text = title
        binding.message.text = message
        binding.positiveButton.text = positiveButtonText
        binding.positiveButton.setOnClickListener {
            dismiss()
            setFragmentResult(
                REQUEST_POSITIVE_BUTTON_KEY,
                bundleOf()
            )
        }
        dialog.setContentView(binding.root)
        return dialog
    }
}