package com.example.lessonscalendar

import kotlinx.coroutines.flow.map

class UserRepository(private val userDao: UserDao) {

    fun getUsers() = userDao.getAll()

    fun getWhatToLearn() = userDao.getAll().map { it ->
        it.sortedBy { it.whatToLearn }
    }

    fun getWhenToStart() = userDao.getAll().map { it ->
        it.sortedBy { it.whenToStart }
    }

    fun getMonthlyCostOfLessons() = userDao.getAll().map { it ->
        it.sortedBy { it.monthlyCostOfLessons }
    }

    fun getXDay() = userDao.getAll().map { it ->
        it.sortedBy { it.xDay }
    }

    fun getXMonth() = userDao.getAll().map { it ->
        it.sortedBy { it.xMonth }
    }

    fun getXYear() = userDao.getAll().map { it ->
        it.sortedBy { it.xYear }
    }

    fun getXYen() = userDao.getAll().map { it ->
        it.sortedBy { it.xYen }
    }
}