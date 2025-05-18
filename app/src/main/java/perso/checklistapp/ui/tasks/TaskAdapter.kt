package perso.checklistapp.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
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

    private var _editMode: Boolean = false

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
        val checkbox = holder.getCheckBoxTask()
        val deleteButton = holder.getButtonDelete()

        setCheckbox(checkbox, task)
        setDeleteButton(deleteButton, task)
    }

    private fun setCheckbox(checkbox: CheckBox, task: Task) {
        checkbox.setOnCheckedChangeListener(null)
        checkbox.text = task.taskName
        checkbox.isChecked = task.isChecked

        checkbox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setChecked(task.id, isChecked)
        }
    }

    private fun setDeleteButton(deleteButton: ImageButton, task: Task) {
        deleteButton.visibility = when (_editMode) {
            false -> View.GONE
            true  -> View.VISIBLE
        }
        deleteButton.setOnClickListener {
            deleteTask(task)
        }
    }

    private fun deleteTask(task: Task) {
        viewModel.delete(task)
    }

    fun setEditMode(editMode: Boolean) {
        _editMode = editMode
    }
}