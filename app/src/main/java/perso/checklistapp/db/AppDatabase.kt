package perso.checklistapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import perso.checklistapp.dao.ListDao
import perso.checklistapp.dao.TaskDao
import perso.checklistapp.model.Task
import perso.checklistapp.model.TaskList

@Database(entities = [Task::class, TaskList::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun listDao(): ListDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "checklist-app-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}