package com.example.lessonscalendar

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    //Room用
    lateinit var userAppDatabase: UserAppDatabase
    lateinit var userDao: UserDao
    lateinit var userEntity: UserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userAppDatabase = UserAppDatabase.getUserAppDatabase(this)
        userDao = userAppDatabase.userDao()
    }

    //画面がタッチされた時に反応するやつ
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // InputMethodManagerを取得
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //背景のlinearLayoutを取得
        val constraintLayout = findViewById<ConstraintLayout>(R.id.activity_main)
        // キーボードを閉じる
        inputMethodManager.hideSoftInputFromWindow(
            constraintLayout.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
        return false
    }
}