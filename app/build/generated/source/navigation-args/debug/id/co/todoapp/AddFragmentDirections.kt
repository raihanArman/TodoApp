package id.co.todoapp

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

public class AddFragmentDirections private constructor() {
  public companion object {
    public fun actionAddFragmentToListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_addFragment_to_listFragment)
  }
}
