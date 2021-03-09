package id.co.todoapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.todoapp.data.viewmodel.ToDoViewModel
import id.co.todoapp.databinding.FragmentListBinding
import id.co.todoapp.fragments.SharedViewModel
import id.co.todoapp.fragments.list.ListAdapter


class ListFragment : Fragment() {

    private val listAdapter: ListAdapter by lazy { ListAdapter(requireContext()) }
    private val viewModel : ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private var _dataBinding : FragmentListBinding ?= null
    private val dataBinding get() = _dataBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container,  false)
        dataBinding.lifecycleOwner = this
        dataBinding.mSharedViewModel = mSharedViewModel

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter=listAdapter
        }

        viewModel.getAllData.observe(viewLifecycleOwner, Observer {data->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            listAdapter.setData(data)
        })


        dataBinding.listLayout.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        setHasOptionsMenu(true)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all){
            confirmRemoveAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmRemoveAll() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            viewModel.deleteAll()
            Toast.makeText(requireContext(), "Successfully Removed All", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete Everything ?")
        builder.setMessage("Are you sure you want to remove everything ?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _dataBinding = null
    }
}