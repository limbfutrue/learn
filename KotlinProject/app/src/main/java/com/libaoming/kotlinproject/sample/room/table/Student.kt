package com.libaoming.kotlinproject.sample.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 注解 @Entity 代表这是一张表
 */
@Entity
class Student(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "age") var age: Int,
    @ColumnInfo(name = "address") var address: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    override fun toString(): String {
        return "Student(uid=$uid, name=$name, age=$age, address=$address)"
    }

}