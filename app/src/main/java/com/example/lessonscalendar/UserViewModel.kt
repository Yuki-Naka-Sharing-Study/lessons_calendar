package com.example.lessonscalendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class UserViewModel(
    private val repo: UserRepository
): ViewModel() {
    val users: LiveData<List<UserEntity>>
            = repo.getUsers().asLiveData()
    val whatToLearn: LiveData<List<UserEntity>>
            = repo.getWhatToLearn().asLiveData()
    val whenToStart: LiveData<List<UserEntity>>
            = repo.getWhenToStart().asLiveData()
    val monthlyCostOfLessons: LiveData<List<UserEntity>>
            = repo.getMonthlyCostOfLessons().asLiveData()
    val xDay: LiveData<List<UserEntity>>
            = repo.getXDay().asLiveData()
    val xMonth: LiveData<List<UserEntity>>
            = repo.getXMonth().asLiveData()
    val xYear: LiveData<List<UserEntity>>
            = repo.getXYear().asLiveData()
    val xYen: LiveData<List<UserEntity>>
            = repo.getXYen().asLiveData()
}