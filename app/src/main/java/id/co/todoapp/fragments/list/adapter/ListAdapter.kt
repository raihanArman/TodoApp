package id.co.todoapp.fragments.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.todoapp.data.models.TodoData
import id.co.todoapp.databinding.RowLayoutBinding

class ListAdapter(val context: Context) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<TodoData>()

    class MyViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(todoData: TodoData){
            binding.toDoData = todoData
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
//        holder.binding.tvTitle.text = dataList[position].title
//        holder.binding.tvDesc.text = dataList[position].description
//        holder.itemView.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
//            holder.itemView.findNavController().navigate(action)
//        }
//
//        val priority = dataList[position].priority
//        when(priority){
//            Priority.HIGH -> holder.binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red))
//            Priority.MEDIUM -> holder.binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
//            Priority.LOW -> holder.binding.priorityIndicator.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green))
//        }
    }

    fun setData(todoData:List<TodoData>){
        val toDoDiffUtil = ToDoDiffUtil(dataList, todoData)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = todoData
        toDoDiffResult.dispatchUpdatesTo(this)
    }

}