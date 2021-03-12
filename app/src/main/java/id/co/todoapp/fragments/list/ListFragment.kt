package id.co.todoapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.co.todoapp.R
import id.co.todoapp.data.models.TodoData
import id.co.todoapp.data.viewmodel.ToDoViewModel
import id.co.todoapp.databinding.FragmentListBinding
import id.co.todoapp.fragments.SharedViewModel
import id.co.todoapp.fragments.list.adapter.ListAdapter
import id.co.todoapp.util.hideKeyboard
import id.co.todoapp.util.observeOnce
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


class ListFragment : Fragment(), SearchView.OnQueryTextListener {

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
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter=listAdapter
            itemAnimator = SlideInUpAnimator().apply {
                addDuration = 300
            }
            swipeToDelete(this)
        }

        viewModel.getAllData.observe(viewLifecycleOwner, Observer {data->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            listAdapter.setData(data)
        })


        dataBinding.listLayout.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        setHasOptionsMenu(true)

        hideKeyboard(requireActivity())

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all ->{
                confirmRemoveAll()
            }
            R.id.menu_priority_high ->{
                viewModel.sortByHighPriority.observe(viewLifecycleOwner, Observer {
                    listAdapter.setData(it)
                })
            }
            R.id.menu_priority_low ->{
                viewModel.sortByLowPriority.observe(viewLifecycleOwner, Observer {
                    listAdapter.setData(it)
                })
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallback = object: SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemToDelete = listAdapter.dataList[viewHolder.adapterPosition]
                viewModel.deleteItem(itemToDelete)
                listAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                restoreDeleteData(viewHolder.itemView, itemToDelete)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    private fun restoreDeleteData(itemView: View, itemToDelete: TodoData) {
        val snackbar=  Snackbar.make(
                itemView, "Delete ${itemToDelete.title}",
                Snackbar.LENGTH_LONG
        )
        snackbar.setAction("Undo"){
            viewModel.insertData(itemToDelete)
        }
        snackbar.show()
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null){
            searchThroughDatabase(query)
        }
        return true
    }

    private fun searchThroughDatabase(query: String) {
        var searchQuery = "%$query%"

        viewModel.searchDatabase(searchQuery).observeOnce(viewLifecycleOwner, Observer { list ->
            list?.let {
                listAdapter.setData(it)
            }
        })
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null){
            searchThroughDatabase(query)
        }
        return true
    }
}