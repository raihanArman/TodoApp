package id.co.todoapp.fragments.list

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.todoapp.R
import id.co.todoapp.data.models.Priority
import id.co.todoapp.data.models.TodoData
import id.co.todoapp.databinding.RowLayoutBinding

class ListAdapter(val context: Context) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<TodoData>()

    class MyViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: RowLayoutBinding = DataBindingUtil.inflate(view, R.layout.row_layout, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvTitle.text = dataList[position].title
        holder.binding.tvDesc.text = dataList[position].description

        val priority = dataList[position].priority
        when(priority){
            Priority.HIGH -> holder.binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red))
            Priority.MEDIUM -> holder.binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
            Priority.LOW -> holder.binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green))
        }
    }

    fun setData(todoData:List<TodoData>){
        this.dataList = todoData
        notifyDataSetChanged()
    }

}