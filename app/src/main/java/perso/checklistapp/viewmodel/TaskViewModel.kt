package perso.checklistapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import perso.checklistapp.db.AppDatabase
import perso.checklistapp.model.Task
import perso.checklistapp.model.TaskList

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val _taskDao = AppDatabase.getInstance(application).taskDao()
    val allTasks: LiveData<List<Task>> = _taskDao.getAll().asLiveData()

    fun getTasksOfList(list: TaskList): LiveData<List<Task>> = _taskDao.getTasksOfList(list.id).asLiveData()

    fun insert(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        _taskDao.insert(task)
    }

    fun delete(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        _taskDao.delete(task)
    }
}