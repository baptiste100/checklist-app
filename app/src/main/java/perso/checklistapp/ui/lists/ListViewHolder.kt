package perso.checklistapp.ui.tasks

import android.annotation.SuppressLint
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import perso.checklistapp.R

class ListViewHolder(view : View): RecyclerView.ViewHolder(view) {
    private val _editTextListName = view.findViewById<EditText>(R.id.editTextNewList)

    fun getEditTextListName() : EditText {
        return _editTextListName
    }
}
