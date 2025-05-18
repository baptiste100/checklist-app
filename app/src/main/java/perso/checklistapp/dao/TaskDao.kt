package perso.checklistapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import perso.checklistapp.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE list_id = :id")
    fun getTasksOfList(id: Int) : Flow<List<Task>>

    @Query("UPDATE task SET is_checked = :checked WHERE id = :taskId")
    fun setChecked(taskId: Int, checked: Boolean)

    @Insert
    fun insert(vararg task: Task)

    @Delete
    fun delete(task: Task)
}