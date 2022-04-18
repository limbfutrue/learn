package com.libaoming.kotlinproject.sample.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.libaoming.kotlinproject.sample.room.dao.StudentDao
import com.libaoming.kotlinproject.sample.room.table.Student

@Database(entities = [Student::class],version = 1,exportSchema = false)
abstract class LimbDataBase : RoomDatabase() {
    abstract fun stuDao(): StudentDao
}