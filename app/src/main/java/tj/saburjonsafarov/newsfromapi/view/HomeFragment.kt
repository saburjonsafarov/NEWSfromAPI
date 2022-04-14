package tj.saburjonsafarov.newsfromapi.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.repository.adapters.MainAdapter
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel.Articles
import tj.saburjonsafarov.newsfromapi.repository.DBHelper
import tj.saburjonsafarov.newsfromapi.repository.MainRepository
import tj.saburjonsafarov.newsfromapi.vm.HomeViewModel

class HomeFragment : Fragment(), View.OnClickListener, View.OnLongClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: HomeViewModel
    private lateinit var refreshLayout: SwipeRefreshLayout
    var q = "Sport"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[HomeViewModel(Application())::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.homeRecyclerView)
        refreshLayout = view.findViewById(R.id.homeRefreshLayout)

        return view
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshLayout.setColorSchemeColors(
            Color.rgb(
                (0..255).random(),
                (0..255).random(),
                (0..255).random()
            )
        )

        refreshLayout.isRefreshing = true

        recyclerView.layoutManager = LinearLayoutManager(context)


        viewModel.get(q).observe(viewLifecycleOwner) {
            recyclerView.adapter = MainAdapter(it, requireContext(), this, this)


            refreshLayout.setOnRefreshListener {
                refreshLayout.isRefreshing = true
                Toast.makeText(context, "refreshed !", Toast.LENGTH_SHORT).show()
                recyclerView.adapter = MainAdapter(it, requireContext(), this, this)
                refreshLayout.setColorSchemeColors(
                    Color.rgb(
                        (0..255).random(),
                        (0..255).random(),
                        (0..255).random()
                    )
                )
                refreshLayout.isRefreshing = false
            }

            refreshLayout.isRefreshing = false
            Toast.makeText(context, "is loaded !", Toast.LENGTH_SHORT).show()

        }
    }


    override fun onClick(v: View?) {
        v?.let {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(it.tag as Articles))
                .addToBackStack(null)
                .commit()

            saveNew(DBHelper.HISTORY_TABLE, it.tag as Articles)

        }
    }

    override fun onLongClick(p0: View?): Boolean {
        p0?.let {
            val alert = AlertDialog.Builder(requireContext())
            alert.setIcon(R.drawable.ic_favorites)
            alert.setTitle("Add to favorites ?")
            alert.setNegativeButton("no !") { _, _ ->
            }
            alert.setPositiveButton("yes !") { _, _ ->
                saveNew(DBHelper.FAVORITES_TABLE, it.tag as Articles)
            }
            alert.create()
            alert.show()
            return true
        }
        return false
    }

    private fun saveNew(table: String, data: Articles) {
        MainRepository(requireContext())
            .saveNew(table, data)
    }

    companion object {
        fun newInstance(q: String): HomeFragment {
            val fragment = HomeFragment()
            fragment.q = q
            return fragment
        }
    }

}
