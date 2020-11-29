package com.example.recyclerviewapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class StudentsRecycleAdapter(val context: Context, val students: List<Student> ) :
    RecyclerView.Adapter<StudentsRecycleAdapter.ViewHolder>() {

    val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.student_list_view, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]
        holder.textName.text = student.name
        holder.textClassName.text = student.className
        holder.doneButton.isChecked = student.done
        holder.clickedStudentPosition = position
    }

    fun removeStudent(clickedStudentPosition: Int) {
        val dialogBuilder = AlertDialog.Builder(context)

        dialogBuilder.setTitle("Remove student?")
            .setMessage("Do you want to remove ${DataManager.students[clickedStudentPosition].name}?")
            .setPositiveButton("Remove") { dialog, which ->
                DataManager.students.removeAt(clickedStudentPosition)
                notifyDataSetChanged()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()
        alert.show()

    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.nameTextView)
        val textClassName = itemView.findViewById<TextView>(R.id.classTextView)
        val doneButton = itemView.findViewById<CheckBox>(R.id.checkBox)
        val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
        var clickedStudentPosition = 0

        init {
            itemView.setOnClickListener{
                val intent = Intent(context, CreateAndEditStudentActivity::class.java)
                intent.putExtra(STUDENT_POSITION_KEY, clickedStudentPosition)
                context.startActivity(intent)
            }

            doneButton.setOnClickListener{
                DataManager.students[clickedStudentPosition].done = doneButton.isChecked
                Toast.makeText(context, "${DataManager.students[clickedStudentPosition].name}" +
                        " is ${DataManager.students[clickedStudentPosition].done}",
                        Toast.LENGTH_SHORT).show()
            }

            deleteButton.setOnClickListener{
                removeStudent(clickedStudentPosition)
            }
        }
    }

}