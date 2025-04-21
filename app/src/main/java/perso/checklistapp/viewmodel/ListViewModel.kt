package perso.checklistapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import perso.checklistapp.db.AppDatabase
import perso.checklistapp.model.TaskList

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val _listDao = AppDatabase.getInstance(application).listDao()
    val allLists : LiveData<List<TaskList>> = _listDao.getAll().asLiveData()

    fun insert(list: TaskList) = viewModelScope.launch(Dispatchers.IO) {
        _listDao.insert(list)
    }

    fun delete(list: TaskList) = viewModelScope.launch(Dispatchers.IO) {
        _listDao.delete(list)
    }
}