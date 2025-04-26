package perso.checklistapp.ui.tasks

import android.annotation.SuppressLint
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import perso.checklistapp.R
import perso.checklistapp.model.TaskList

class ListViewHolder(view : View, val onItemClick: (Int) -> Unit): RecyclerView.ViewHolder(view) {
    private val _textViewListName = view.findViewById<TextView>(R.id.textViewListName)
    private var _listId = 0

    init {
        itemView.setOnClickListener {
            onItemClick(_listId)
        }
    }

    fun bind(taskList: TaskList) {
        _textViewListName.text = taskList.listName
        _listId = taskList.id
    }
}
