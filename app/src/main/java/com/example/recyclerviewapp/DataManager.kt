package com.example.recyclerviewapp

object DataManager {
    val students = mutableListOf<Student>()

    init {
        createMockData()
    }

    fun createMockData() {
        students.add(Student("Student One", "AU20"))
        students.add(Student("Student Two", "AU20", true))
        students.add(Student("Student Three", "AU20", true))
        students.add(Student("Student Four", "AU20"))
        students.add(Student("Student Five", "AU20"))
    }

}