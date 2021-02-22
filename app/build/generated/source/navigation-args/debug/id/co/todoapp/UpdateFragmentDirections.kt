package id.co.todoapp

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class UpdateFragmentDirections private constructor() {
  public companion object {
    public fun actionUpdateFragmentToListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_updateFragment_to_listFragment)
  }
}
