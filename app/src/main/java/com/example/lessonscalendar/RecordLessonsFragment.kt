package com.example.lessonscalendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.lessonscalendar.databinding.FragmentRecordLessonsBinding

class RecordLessonsFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels {
        val userDao = UserAppDatabase.getUserAppDatabase(requireContext()).userDao()
        UserViewModelFactory(UserRepository(userDao))
    }

    private var _binding: FragmentRecordLessonsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordLessonsBinding.inflate(inflater, container, false)

        viewModel.whatToLearn.observe(viewLifecycleOwner, Observer { whatToLearnList ->
            // リストが空でない場合、最初の要素を取得して表示する
            if (whatToLearnList.isNotEmpty()) {
                // 以下の一行に処理が通っていることを確認。
                binding.whatToLearnTextView.text = whatToLearnList[0].whatToLearn
            } else {
                binding.whatToLearnTextView.text = "まだ何も習い事を始めていません。" // 空の場合のメッセージ
            }
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.backToFragmentHomeView.setOnClickListener {
            navController.navigate(R.id.action_recordLessonsFragment_to_homeFragment)
        }
    }
}