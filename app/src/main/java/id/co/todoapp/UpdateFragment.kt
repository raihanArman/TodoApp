package id.co.todoapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import id.co.todoapp.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    lateinit var dataBindning: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBindning = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)
        // Inflate the layout for this fragment
        return dataBindning.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_fragment_menu, menu)
    }
}