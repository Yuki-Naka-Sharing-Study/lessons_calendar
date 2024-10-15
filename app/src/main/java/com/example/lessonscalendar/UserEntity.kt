package com.example.lessonscalendar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    //// fragment_start_lessons

    // whatToLearn
    @ColumnInfo(name = "what_to_learn")
    var whatToLearn: String = "",

    // whenToStart
    @ColumnInfo(name = "when_to_start")
    var whenToStart: String = "",


    // monthlyCostOfLessons
    @ColumnInfo(name = "monthly_cost_of_lessons")
    var monthlyCostOfLessons: String = "",

    //// fragment_record_lessons

    // xDay
    @ColumnInfo(name = "x_day")
    var xDay: String = "",

    // xMonth
    @ColumnInfo(name = "x_month")
    var xMonth: String = "",

    // xYear
    @ColumnInfo(name = "x_year")
    var xYear: String = "",

    // xYen
    @ColumnInfo(name = "x_yen")
    var xYen: String = "",
)
