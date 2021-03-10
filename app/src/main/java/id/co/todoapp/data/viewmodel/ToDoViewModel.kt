package id.co.todoapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import id.co.todoapp.data.ToDoDatabase
import id.co.todoapp.data.models.TodoData
import id.co.todoapp.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {
    private val toDoDao = ToDoDatabase.getDatabase(application).toDoDao()
    private val repository: ToDoRepository

    val getAllData: LiveData<List<TodoData>>
    val sortByHighPriority: LiveData<List<TodoData>>
    val sortByLowPriority: LiveData<List<TodoData>>

    init {
        repository = ToDoRepository(toDoDao)
        getAllData = repository.getAllData
        sortByHighPriority = repository.sortByHighPriority
        sortByLowPriority = repository.sortByLowPriority
    }

    fun insertData(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(todoData)
        }
    }

    fun updateData(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(todoData)
        }
    }

    fun deleteItem(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(todoData)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<TodoData>>{
        return repository.searchDatabase(searchQuery)
    }

}