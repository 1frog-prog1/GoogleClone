package com.example.googletask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.googletask.ViewModels.ListViewModel
import com.example.domain.models.TaskDomain

private const val TAG = "ListFragment"

class ListFragment : Fragment() {

    private val listViewModel : ListViewModel by lazy {
        ViewModelProviders.of(this).get(ListViewModel::class.java)
    }

    private lateinit var taskRecyclerView : RecyclerView

    private var adapter : TaskAdapter? = TaskAdapter(emptyList())

    private inner class TaskHolder(view : View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private lateinit var task : TaskDomain

        val titleTextView : TextView = itemView.findViewById(R.id.tv_task_title)
        val descriptionTextView : TextView = itemView.findViewById(R.id.tv_task_description)
        val completeImageButton : ImageButton = itemView.findViewById(R.id.imb_task_complete)
        val markImageButton : ImageButton = itemView.findViewById(R.id.imb_task_mark)

        fun bind(task : TaskDomain) {
            this.task = task
            titleTextView.text = task.title
            descriptionTextView.text = task.description

            if (task.isSolved)
                completeImageButton.setBackgroundResource(R.drawable.ic_completed_task)
            else
                completeImageButton.setBackgroundResource(R.drawable.ic_incompleted_task)

            if (task.isMarked)
                markImageButton.setBackgroundResource(R.drawable.ic_marked_task)
            else
                markImageButton.setBackgroundResource(R.drawable.ic_not_marked_task)

        }

        init {
            itemView.setOnClickListener(this)

            completeImageButton.setOnClickListener {
                task.isSolved  = !listViewModel.tasksLiveData.value?.get(position)!!.isSolved
                listViewModel.tasksLiveData.value?.get(position)!!.isSolved = task.isSolved

                if (task.isSolved)
                    completeImageButton.setBackgroundResource(R.drawable.ic_completed_task)
                else
                    completeImageButton.setBackgroundResource(R.drawable.ic_incompleted_task)
            }

            markImageButton.setOnClickListener {
                task.isMarked = !listViewModel.tasksLiveData.value?.get(position)!!.isMarked
                listViewModel.tasksLiveData.value?.get(position)!!.isMarked = task.isMarked

                if (task.isMarked)
                    markImageButton.setBackgroundResource(R.drawable.ic_marked_task)
                else
                    markImageButton.setBackgroundResource(R.drawable.ic_not_marked_task)
            }


        }

        override fun onClick(v: View?) {
            Toast.makeText(context, "${task.title} pressed!"
                , Toast.LENGTH_SHORT)
                .show()
        }

    }

    private inner class TaskAdapter(var Tasks : List<TaskDomain>)
        : RecyclerView.Adapter<TaskHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
            val view = layoutInflater.inflate(R.layout.list_item_task, parent, false)
            return TaskHolder(view)
        }

        override fun onBindViewHolder(holder: TaskHolder, position: Int) {
            val task = Tasks[position]
            holder.bind(task)
        }

        override fun getItemCount(): Int {
            return Tasks.size
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        taskRecyclerView = view.findViewById(R.id.rv_tasks)
        taskRecyclerView.layoutManager = LinearLayoutManager(context)
        taskRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel.tasksLiveData.observe(
            viewLifecycleOwner,
            Observer { tasks ->
                tasks?.let {
                    Log.i(TAG, "Got tasks ${tasks.size}")
                    updateUI(tasks)
                }
            }
        )
    }

    private fun updateUI(tasks : List<TaskDomain>) {
        adapter = TaskAdapter(tasks)
        taskRecyclerView.adapter = adapter

    }

    companion object {
        fun newInstance() : ListFragment {
            return ListFragment()
        }
    }

}
