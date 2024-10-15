package com.example.lessonscalendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lessonscalendar.databinding.FragmentStartLessonsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StartLessonsFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels {
        val userDao = UserAppDatabase.getUserAppDatabase(requireContext()).userDao()
        UserViewModelFactory(UserRepository(userDao))
    }

    private var _binding: FragmentStartLessonsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartLessonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.backToFragmentHomeView.setOnClickListener {
            navController.navigate(R.id.action_startLessonsFragment_to_homeFragment)
        }

        binding.startLessonButton.setOnClickListener {
            // りくとさんから習った空文字判定をしないといけない。
            binding.lessonsEditText.setOnClickListener {
                if (binding.lessonsEditText.text.toString() == "") {
                    EnterLessonDialog.Builder(this)
                        .setTitle("別の名前を選択してください。")
                        .setMessage("フォルダ名は空白にできません。")
                        .setPositiveButton("OK")
                        .build()
                        .show(childFragmentManager, EnterLessonDialog::class.simpleName)
                } else {
                    val mainActivity = activity as MainActivity?
                    if (mainActivity != null) {

                        lifecycleScope.launch(Dispatchers.IO) {
                            withContext(Dispatchers.Default) {
                                mainActivity.userDao.insertUser(
                                    UserEntity(whatToLearn = binding.lessonsEditText.text.toString())
                                )
                            }
                        }
                    }
                    navController.navigate(R.id.action_startLessonsFragment_to_homeFragment)
                }
            }
        }
    }
}