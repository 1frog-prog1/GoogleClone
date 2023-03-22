package com.example.googletask

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProviders
import com.example.domain.models.TaskDomain
import com.example.googletask.ViewModels.TaskViewModel
import java.util.*
import androidx.lifecycle.Observer

private const val TAG = "TaskFragment"
private const val ARG_TASK_ID = "task_id"

class TaskFragment : Fragment() {

    private  val taskViewModel : TaskViewModel by lazy {
        ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }

   private lateinit var task : TaskDomain

   private lateinit var titleField : EditText
   private lateinit var descriptionField : EditText
   private lateinit var markButton : ImageButton
   private lateinit var solveButton : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        task = TaskDomain()
        val taskId : UUID = arguments?.getSerializable(ARG_TASK_ID) as UUID

        taskViewModel.loadTask(taskId)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskViewModel.taskLiveData.observe(
            viewLifecycleOwner,
            Observer { task ->
                task?.let {
                    this.task = task
                    updateUI()
                }
            }
        )
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

    override fun onStop() {
        super.onStop()
        taskViewModel.saveTask(task)
    }

    private fun updateUI() {
        titleField.setText(task.title)
        descriptionField.setText(task.description)

        if (task.isSolved)
            solveButton.setBackgroundResource(R.drawable.ic_completed_task)
        else
            solveButton.setBackgroundResource(R.drawable.ic_incompleted_task)

        if (task.isMarked)
            markButton.setBackgroundResource(R.drawable.ic_marked_task)
        else
            markButton.setBackgroundResource(R.drawable.ic_not_marked_task)

    }

    companion object{
        fun newInstance(taskId : UUID) : TaskFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TASK_ID, taskId)
            }

            return TaskFragment().apply {
                arguments = args
            }
        }
    }

}