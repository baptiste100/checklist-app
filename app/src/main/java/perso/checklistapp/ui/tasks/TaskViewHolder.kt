package perso.checklistapp.ui.tasks

import android.annotation.SuppressLint
import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import perso.checklistapp.R

class TaskViewHolder(view : View): RecyclerView.ViewHolder(view) {
    private val checkBoxTask: CheckBox = view.findViewById(R.id.checkBoxTask)

    fun getCheckBoxTask(): CheckBox {
        return checkBoxTask
    }
}
