package com.example.lessonscalendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lessonscalendar.databinding.FragmentRecordLessonsBinding

class RecordLessonsFragment : Fragment() {

    private var _binding: FragmentRecordLessonsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordLessonsBinding.inflate(inflater, container, false)
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