package perso.checklistapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import perso.checklistapp.model.Task
import perso.checklistapp.model.TaskList

@Dao
interface ListDao {
    @Query("SELECT * FROM list")
    fun getAll(): Flow<List<TaskList>>

    @Insert
    fun insert(vararg list: TaskList)

    @Delete
    fun delete(list: TaskList)
}