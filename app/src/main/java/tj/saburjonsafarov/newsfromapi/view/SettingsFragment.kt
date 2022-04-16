package tj.saburjonsafarov.newsfromapi.view

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.repository.MainRepository
import tj.saburjonsafarov.newsfromapi.repository.adapters.CategoryAdapter
import tj.saburjonsafarov.newsfromapi.vm.SettingsViewModel

class SettingsFragment : Fragment(), CompoundButton.OnCheckedChangeListener {
    lateinit var infoIcon: View
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: SettingsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[SettingsViewModel(Application())::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        recyclerView = view.findViewById(R.id.categoryRecyclerView)
        infoIcon = view.findViewById(R.id.view)
        return view
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getCategory().observe(viewLifecycleOwner) {

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = CategoryAdapter(MainRepository.categories, this)

//        }
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {

        p0?.let {
            MainRepository.categories.forEach { el ->
                if (el.id == it.tag as Int)
                    el.isChecked = p1
            }
        }
    }
}



