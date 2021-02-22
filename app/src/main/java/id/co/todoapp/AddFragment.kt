package id.co.todoapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import id.co.todoapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    lateinit var dataBinding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add, container, false)
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return dataBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }
}