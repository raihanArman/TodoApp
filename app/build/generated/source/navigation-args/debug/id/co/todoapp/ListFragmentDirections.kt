package id.co.todoapp

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import id.co.todoapp.`data`.models.TodoData
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class ListFragmentDirections private constructor() {
  private data class ActionListFragmentToUpdateFragment(
    public val currentItem: TodoData
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_listFragment_to_updateFragment

    @Suppress("CAST_NEVER_SUCCEEDS")
    public override fun getArguments(): Bundle {
      val result = Bundle()
      if (Parcelable::class.java.isAssignableFrom(TodoData::class.java)) {
        result.putParcelable("currentItem", this.currentItem as Parcelable)
      } else if (Serializable::class.java.isAssignableFrom(TodoData::class.java)) {
        result.putSerializable("currentItem", this.currentItem as Serializable)
      } else {
        throw UnsupportedOperationException(TodoData::class.java.name +
            " must implement Parcelable or Serializable or must be an Enum.")
      }
      return result
    }
  }

  public companion object {
    public fun actionListFragmentToAddFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_listFragment_to_addFragment)

    public fun actionListFragmentToUpdateFragment(currentItem: TodoData): NavDirections =
        ActionListFragmentToUpdateFragment(currentItem)
  }
}
