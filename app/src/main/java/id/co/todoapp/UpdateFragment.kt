package id.co.todoapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import id.co.todoapp.data.models.Priority
import id.co.todoapp.data.models.TodoData
import id.co.todoapp.data.viewmodel.ToDoViewModel
import id.co.todoapp.databinding.FragmentUpdateBinding
import id.co.todoapp.fragments.SharedViewModel

class UpdateFragment : Fragment() {

    private var _dataBindning: FragmentUpdateBinding ?= null
    private val dataBindning get() = _dataBindning!!
    private val args by navArgs<UpdateFragmentArgs>()
    private val mShareViewModel: SharedViewModel by viewModels()
    private val mTodoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _dataBindning = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return dataBindning.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBindning.args = args

        dataBindning.propertiesCurrent.onItemSelectedListener = mShareViewModel.listener

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_save){
            updateItem()
        }else if(item.itemId == R.id.menu_delete){
            confirmItemSelected()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmItemSelected() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mTodoViewModel.deleteItem(args.currentItem)
            Toast.makeText(requireContext(), "Successfully Removed ${args.currentItem.title}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete ${args.currentItem.title} ?")
        builder.setMessage("Are you sure you want to remove ${args.currentItem.title}")
        builder.create().show()
    }

    private fun updateItem() {
        val title = dataBindning.etTitleCurrent.text.toString()
        val description = dataBindning.etDescriptionCurrent.text.toString()
        val getPriority = dataBindning.propertiesCurrent.selectedItem.toString()

        val validation = mShareViewModel.verifyDataFromUser(title, description)

        if(validation){
            val updateItem = TodoData(
                args.currentItem.id,
                title,
                mShareViewModel.parsePriority(getPriority),
                description
            )
            mTodoViewModel.updateData(updateItem)
            Toast.makeText(requireContext(), "Successfully update", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all field", Toast.LENGTH_LONG).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _dataBindning = null
    }
}