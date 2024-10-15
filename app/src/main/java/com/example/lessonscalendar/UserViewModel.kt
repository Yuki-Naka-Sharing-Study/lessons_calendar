package com.example.lessonscalendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class UserViewModel(
    private val repo: UserRepository
): ViewModel() {
    val users: LiveData<List<UserEntity>>
            = repo.getUsers().asLiveData()
    val WhatToLearn: LiveData<List<UserEntity>>
            = repo.getWhatToLearn().asLiveData()
    val WhenToStart: LiveData<List<UserEntity>>
            = repo.getWhenToStart().asLiveData()
    val MonthlyCostOfLessons: LiveData<List<UserEntity>>
            = repo.getMonthlyCostOfLessons().asLiveData()
    val XDay: LiveData<List<UserEntity>>
            = repo.getXDay().asLiveData()
    val XMonth: LiveData<List<UserEntity>>
            = repo.getXMonth().asLiveData()
    val XYear: LiveData<List<UserEntity>>
            = repo.getXYear().asLiveData()
    val XYen: LiveData<List<UserEntity>>
            = repo.getXYen().asLiveData()
}