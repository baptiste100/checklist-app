package perso.checklistapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import perso.checklistapp.model.Task
import perso.checklistapp.model.TaskList
import perso.checklistapp.model.relation.ListWithTasks

@Dao
interface ListDao {
    @Query("SELECT * FROM list")
    fun getAll(): Flow<List<TaskList>>

    @Query("SELECT * FROM list WHERE id = :id")
    fun getListWithTasks(id: Int) : Flow<ListWithTasks>

    @Insert
    fun insert(vararg list: TaskList)

    @Delete
    fun delete(list: TaskList)
}