package id.co.todoapp

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class ListFragmentDirections private constructor() {
  public companion object {
    public fun actionListFragmentToAddFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_listFragment_to_addFragment)

    public fun actionListFragmentToUpdateFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_listFragment_to_updateFragment)
  }
}
