package com.example.googletask

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import com.example.domain.models.TaskDomain

private const val TAG = "TaskFragment"

class TaskFragment : Fragment() {

   private lateinit var task : TaskDomain

   private lateinit var titleField : EditText
   private lateinit var descriptionField : EditText
   private lateinit var markButton : ImageButton
   private lateinit var solveButton : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        task = TaskDomain()

        Log.d(TAG, "TaskFragment was created")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_task,
                container, false)

        titleField = view.findViewById(R.id.task_title) as EditText
            descriptionField = view.findViewById(R.id.description_title)
            markButton = view.findViewById(R.id.button_mark)
            solveButton = view.findViewById(R.id.button_solve)


        return view
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                task.title =  s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }


        }
    }

}