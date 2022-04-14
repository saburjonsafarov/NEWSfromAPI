package tj.saburjonsafarov.newsfromapi.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.repository.MainRepository
import tj.saburjonsafarov.newsfromapi.repository.adapters.CategoryAdapter

class SettingsFragment : Fragment(), CompoundButton.OnCheckedChangeListener {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        recyclerView = view.findViewById(R.id.categoryRecyclerView)
        return view
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CategoryAdapter(MainRepository.categories, this)
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



