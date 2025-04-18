package perso.checklistapp.dao

import androidx.room.Dao
import androidx.room.Query
import perso.checklistapp.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>
}