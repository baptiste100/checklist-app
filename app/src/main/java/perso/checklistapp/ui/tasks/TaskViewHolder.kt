package perso.checklistapp.ui.tasks

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import perso.checklistapp.R

class TaskViewHolder(view : View): RecyclerView.ViewHolder(view) {
    private val textViewTask: TextView = view.findViewById(R.id.textViewTask)

    fun getTextViewTask(): TextView {
        return textViewTask
    }
}
