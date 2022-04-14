package tj.saburjonsafarov.newsfromapi.view

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.repository.DBHelper
import tj.saburjonsafarov.newsfromapi.repository.MainRepository
import tj.saburjonsafarov.newsfromapi.repository.adapters.MainAdapter
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel
import tj.saburjonsafarov.newsfromapi.vm.HistoryViewModel

@Deprecated("zameneno na universalniy fragment")
class HistoryFragment : Fragment(), View.OnClickListener, View.OnLongClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: HistoryViewModel
    lateinit var clear: Button


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[HistoryViewModel(Application())::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        recyclerView = view.findViewById(R.id.historyRecyclerView)
        clear = view.findViewById(R.id.historyClearButton)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getHistory().observe(viewLifecycleOwner) {
            recyclerView.adapter = MainAdapter(it, requireContext(), this, this)
        }


        clear.setOnClickListener {
            delete()
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, HistoryFragment())
                .commit()

            Toast.makeText(context, "cleared", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onClick(p0: View?) {
        p0?.let {

        }
    }

    override fun onLongClick(p0: View?): Boolean {
        p0?.let {
            AlertDialog
                .Builder(requireContext())
                .setMessage("add to favorites ?")
                .setIcon(R.drawable.ic_favorites)
                .setPositiveButton("yes") { _, _ ->
                    saveNew(DBHelper.FAVORITES_TABLE, it.tag as EverythingModel.Articles)
                }
                .create()
                .show()
            return true
        }
        return false
    }

    fun saveNew(table: String, data: EverythingModel.Articles) {
        MainRepository(requireContext())
            .saveNew(table, data)
    }


    fun delete() {
        DBHelper(requireContext())
            .delete(DBHelper.HISTORY_TABLE)
    }

}