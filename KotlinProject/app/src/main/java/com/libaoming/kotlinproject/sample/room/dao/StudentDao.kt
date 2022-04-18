package com.libaoming.kotlinproject.sample.room.dao

import androidx.room.*
import com.libaoming.kotlinproject.sample.room.table.Student

/**
 * 操作数据库的dao层
 * 进行增删改查
 */
@Dao
interface StudentDao {
    /**
     * 插入数据到数据库
     * vararg student:Student 类似于 insert(Student ... student)
     */
    @Insert
    fun insert(vararg student:Student)

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("select * from Student")
    fun query():List<Student>
}