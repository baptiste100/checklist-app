package perso.checklistapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "task_name") val taskName: String
)