package perso.checklistapp.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import perso.checklistapp.R
import perso.checklistapp.model.Task
import perso.checklistapp.model.TaskList
import perso.checklistapp.viewmodel.ListViewModel

class TaskListAdapter(private val viewModel: ListViewModel) : ListAdapter<TaskList, ListViewHolder>(DIFF_CALLBACK) {

    private var _editMode: Boolean = false

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TaskList>() {
            override fun areItemsTheSame(oldItem: TaskList, newItem: TaskList): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TaskList, newItem: TaskList): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_view_holder, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val list = getItem(position)
        holder.getEditTextListName().setText(list.listName)
    }
}