package perso.checklistapp.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import perso.checklistapp.model.TaskList
import perso.checklistapp.model.Task

data class ListWithTasks (
    @Embedded val list: TaskList,
    @Relation (
        parentColumn = "id",
        entityColumn = "list_id"
    )
    val tasks: List<Task>
)