package id.co.todoapp.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import id.co.todoapp.data.models.TodoData

class ToDoDiffUtil(
        private val oldList: List<TodoData>,
        private val newList: List<TodoData>
): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id &&
                oldList[oldItemPosition].title == newList[newItemPosition].title&&
                oldList[oldItemPosition].description == newList[newItemPosition].description&&
                oldList[oldItemPosition].priority == newList[newItemPosition].priority
    }
}