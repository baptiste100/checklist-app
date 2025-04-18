package perso.checklistapp.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import perso.checklistapp.R
import perso.checklistapp.ui.tasks.TaskViewHolder

class TaskAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_view_holder, parent, false)

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.getTextViewTask().text = dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}