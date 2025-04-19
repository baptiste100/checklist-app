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

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val _taskDao = AppDatabase.getInstance(application).taskDao()
    val _allTasks: LiveData<List<Task>> = _taskDao.getAll().asLiveData()

    fun insert(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        _taskDao.insert(task)
    }
}