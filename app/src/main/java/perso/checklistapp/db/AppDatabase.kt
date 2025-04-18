package perso.checklistapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import perso.checklistapp.dao.TaskDao
import perso.checklistapp.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}