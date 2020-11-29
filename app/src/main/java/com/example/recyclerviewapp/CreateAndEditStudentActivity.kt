package com.example.recyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.student_list_view.*

const val STUDENT_POSITION_KEY = "STUDENT_POSITION"
const val POSITION_NOT_SET = -1

class CreateAndEditStudentActivity : AppCompatActivity() {

    lateinit var nameTextView : EditText
    lateinit var classTextView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_and_edit_student)

        nameTextView = findViewById(R.id.editNameText)
        classTextView = findViewById(R.id.editClassText)
        val saveButton = findViewById<Button>(R.id.saveButton)

        val studentPosition = intent.getIntExtra(STUDENT_POSITION_KEY, POSITION_NOT_SET)

        if(studentPosition != POSITION_NOT_SET) {
            displayStudent(studentPosition)
            saveButton.setOnClickListener(){
                editStudent(studentPosition)
            }
        } else {
            saveButton.setOnClickListener{
                addNewStudent()
            }
        }


    }

    fun displayStudent(position: Int) {
        val student = DataManager.students[position]
        nameTextView.setText(student.name)
        classTextView.setText(student.className)
    }

    fun addNewStudent() {
        val name = nameTextView.text.toString()
        val className = classTextView.text.toString()

        val student = Student(name, className)
        DataManager.students.add(student)
        finish()
    }

    fun editStudent(studentPosition: Int){
        DataManager.students[studentPosition].name = nameTextView.text.toString()
        DataManager.students[studentPosition].className = classTextView.text.toString()
        finish()
    }

}