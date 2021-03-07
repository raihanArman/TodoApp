package id.co.todoapp.data.repository

import androidx.lifecycle.LiveData
import id.co.todoapp.data.ToDoDao
import id.co.todoapp.data.models.TodoData

class ToDoRepository(private val toDoDao: ToDoDao) {
    val getAllData: LiveData<List<TodoData>> = toDoDao.getAllData()
    suspend fun insertData(todoData: TodoData){
        toDoDao.insertData(todoData)
    }

    suspend fun updateData(todoData: TodoData){
        toDoDao.updateData(todoData)
    }

    suspend fun deleteItem(todoData: TodoData){
        toDoDao.deleteItem(todoData)
    }

    suspend fun deleteAll(){
        toDoDao.deleteAll()
    }

}