package com.example.lessonscalendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    private lateinit var segmentedControl: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        segmentedControl = view.findViewById(R.id.segmentedControl)

        val navController = findNavController()
        segmentedControl.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {
                R.id.start_lesson -> {
                    navController.navigate(R.id.action_homeFragment_to_startLessonsFragment)
                }
                R.id.record_lesson -> {
                    navController.navigate(R.id.action_homeFragment_to_recordLessonsFragment)
                }
            }
        }
        return view
    }
}