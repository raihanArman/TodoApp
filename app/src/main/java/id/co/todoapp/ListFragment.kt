package id.co.todoapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.todoapp.data.viewmodel.ToDoViewModel
import id.co.todoapp.databinding.FragmentListBinding
import id.co.todoapp.fragments.list.ListAdapter


class ListFragment : Fragment() {

    private val listAdapter: ListAdapter by lazy { ListAdapter(requireContext()) }
    private val viewModel : ToDoViewModel by viewModels()
    lateinit var dataBinding : FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container,  false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter=listAdapter
        }

        viewModel.getAllData.observe(viewLifecycleOwner, Observer {data->
            listAdapter.setData(data)
        })

        dataBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        dataBinding.listLayout.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
}