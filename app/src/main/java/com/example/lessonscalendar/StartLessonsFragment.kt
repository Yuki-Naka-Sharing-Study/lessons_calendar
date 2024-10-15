package com.example.lessonscalendar

import android.app.DatePickerDialog
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
import java.util.Calendar

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

        binding.whenStartButton.setOnClickListener {
            showDatePickerDialog()
        }

        binding.startLessonButton.setOnClickListener {
            // りくとさんから習った空文字判定をしないといけない。

            if (binding.lessonsEditText.text.toString() == "" || binding.monthlyCostEditText.text.toString() == "") {

                EnterLessonDialog.Builder(this)
                    .setTitle("項目欄を全て入力してください。")
                    .setMessage("項目欄は空白にできません。")
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

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            binding.whenStartButton.text = selectedDate
        }, year, month, day).show()
    }
}