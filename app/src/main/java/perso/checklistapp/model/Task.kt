package perso.checklistapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "list_id") val listId: Int?,
    @ColumnInfo(name = "task_name") val taskName: String
)

