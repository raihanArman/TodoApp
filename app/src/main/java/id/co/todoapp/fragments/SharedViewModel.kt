package id.co.todoapp.fragments

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import id.co.todoapp.data.models.Priority

class SharedViewModel(application: Application): AndroidViewModel(application) {
    fun parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" -> {
                Priority.HIGH}
            "Medium Priority" -> {
                Priority.MEDIUM}
            "Low Priority" -> {
                Priority.LOW}
            else -> Priority.LOW
        }
    }

    fun verifyDataFromUser(title: String, descripion: String): Boolean{
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(descripion)){
            false
        }else !(title.isEmpty() || descripion.isEmpty())
    }
}