package id.co.todoapp.fragments

import android.app.Application
import android.graphics.Color.red
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import id.co.todoapp.R
import id.co.todoapp.data.models.Priority

class SharedViewModel(application: Application): AndroidViewModel(application) {

    val listener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                0 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))
                }
                1 ->{
                    (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))
                }
                2 ->{
                    (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))
                }
            }
        }

    }

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