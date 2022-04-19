package com.libaoming.kotlinproject.sample.room

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.libaoming.kotlinproject.BaseActivity
import com.libaoming.kotlinproject.databinding.ActivityRoomBinding
import com.libaoming.kotlinproject.inflate
import com.libaoming.kotlinproject.sample.room.dao.StudentDao
import com.libaoming.kotlinproject.sample.room.db.LimbDataBase
import com.libaoming.kotlinproject.sample.room.table.Student

class RoomActivity : BaseActivity() {
    private val binding by inflate<ActivityRoomBinding>(true)
    override fun initView() {

       MyThread(this).start()
    }

    override fun initData() {

    }

    /**
     * 数据库的操作要放在子线程中执行
     */
    class MyThread(con:Context): Thread() {
        private var context = con
        override fun run() {
            super.run()
            val stuDao: LimbDataBase = Room.databaseBuilder(context,LimbDataBase::class.java,"limb")
//                //允许在主线程操作
//                .allowMainThreadQueries()
                .build()
            val dao: StudentDao = stuDao.stuDao()
            dao.insert(Student("Limb",18,"HN")
                ,Student("ZhangSan",30,"HN")
                ,Student("LiSi",25,"HN"))
            val stu: List<Student> = dao.query()
            (context as Activity).runOnUiThread {
                (context as RoomActivity).binding.tvContent.text = stu.toString()
            }
            Log.e("limb", "run: $stu")
        }
    }
}