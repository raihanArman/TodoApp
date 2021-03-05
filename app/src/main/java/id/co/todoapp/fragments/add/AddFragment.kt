package id.co.todoapp.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import id.co.todoapp.R
import id.co.todoapp.data.models.Priority
import id.co.todoapp.data.models.TodoData
import id.co.todoapp.data.viewmodel.ToDoViewModel
import id.co.todoapp.databinding.FragmentAddBinding
import id.co.todoapp.fragments.SharedViewModel

class AddFragment : Fragment() {

    lateinit var dataBinding: FragmentAddBinding
    val mTodoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        dataBinding.properties.onItemSelectedListener = mSharedViewModel.listener
        return dataBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = dataBinding.etTitle.text.toString()
        val mPriority = dataBinding.properties.selectedItem.toString()
        val mDescription = dataBinding.etDescription.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if (validation){
            val newData = TodoData(
                    0,
                    mTitle,
                    mSharedViewModel.parsePriority(mPriority),
                    mDescription
            )
            mTodoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully addedd !!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields !!", Toast.LENGTH_LONG).show()
        }
    }



}