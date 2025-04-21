package perso.checklistapp.ui.tasks

import android.annotation.SuppressLint
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import perso.checklistapp.R

class ListViewHolder(view : View): RecyclerView.ViewHolder(view) {
    private val _textViewListName = view.findViewById<TextView>(R.id.textViewListName)

    fun getTextViewListName() : TextView {
        return _textViewListName
    }
}
