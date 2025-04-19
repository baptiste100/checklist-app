package perso.checklistapp.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import perso.checklistapp.R
import perso.checklistapp.model.Task
import perso.checklistapp.ui.tasks.TaskViewHolder
import perso.checklistapp.viewmodel.TaskViewModel

class TaskAdapter(private val viewModel: TaskViewModel) : ListAdapter<Task, TaskViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_view_holder, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.getCheckBoxTask().setOnCheckedChangeListener(null)
        holder.getCheckBoxTask().text = task.taskName
        holder.getCheckBoxTask().setOnCheckedChangeListener { _, isChecked -> }
        holder.getButtonDelete().setOnClickListener {
            deleteTask(task)
        }
    }

    private fun deleteTask(task: Task) {
        viewModel.delete(task)
    }
}