package perso.checklistapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import perso.checklistapp.dao.ListDao
import perso.checklistapp.dao.TaskDao
import perso.checklistapp.model.Task
import perso.checklistapp.model.TaskList

@Database(entities = [Task::class, TaskList::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun listDao(): ListDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val MIGRATION_2_3 = object : Migration(2, 3) {
                    override fun migrate(database: SupportSQLiteDatabase) {
                        database.execSQL("ALTER TABLE task ADD COLUMN is_checked INTEGER NOT NULL DEFAULT 0")
                    }
                }

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "checklist-app-db"
                )
                .addMigrations(MIGRATION_2_3)
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}