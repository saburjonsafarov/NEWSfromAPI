package tj.saburjonsafarov.newsfromapi.view

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.repository.adapters.MainAdapter
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel
import tj.saburjonsafarov.newsfromapi.repository.DBHelper
import tj.saburjonsafarov.newsfromapi.vm.SavedsViewModel

class SavedsFragment : Fragment(), View.OnClickListener, View.OnLongClickListener {
    private lateinit var table: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: SavedsViewModel
    private lateinit var blankPageImageView: ImageView
    var q = "Everything"


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[SavedsViewModel(Application())::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_saveds, container, false)
        recyclerView = view.findViewById(R.id.baseFragmentRecyclerView)
        blankPageImageView = view.findViewById(R.id.isBlankImageView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getNew(table).observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                blankPageImageView.isVisible = false
                recyclerView.adapter = MainAdapter(it, requireContext(), this, this)

            } else
                blankPageImageView.isVisible = true
            if (table == DBHelper.HISTORY_TABLE)
                blankPageImageView.setImageResource(R.drawable.history_banner)
            else if (table == DBHelper.FAVORITES_TABLE)
                blankPageImageView.setImageResource(R.drawable.favorites_banner)
        }
    }


    companion object {
        fun newInstance(table: String): SavedsFragment {
            val fragment = SavedsFragment()
            fragment.table = table
            return fragment
        }
    }

    override fun onClick(p0: View?) {

        p0?.let {
            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.container,
                    DetailFragment.newInstance(it.tag as EverythingModel.Articles)
                )
                .addToBackStack(null)
                .commit()

            if (table == DBHelper.FAVORITES_TABLE) {
                DBHelper(requireContext())
                    .saveNews(DBHelper.HISTORY_TABLE, it.tag as EverythingModel.Articles)
            }
        }
    }

    override fun onLongClick(p0: View?): Boolean {

        p0?.let {
            AlertDialog
                .Builder(requireContext())
                .setIcon(R.drawable.ic_baseline_delete_forever_24)
                .setTitle("delete news from $table")
                .setMessage("clear all or delete only this ?")
                .setPositiveButton("all") { _, _ ->
                    DBHelper(requireContext())
                        .delete(table)
                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, newInstance(table))
                        .commit()
                    Toast.makeText(
                        context,
                        "new is deleted !",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton("only this") { _, _ ->
                    DBHelper(requireContext())
                        .delete(table, (it.tag as EverythingModel.Articles).url)
                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, newInstance(table))
                        .commit()
                    Toast.makeText(context, "cleared", Toast.LENGTH_SHORT).show()
                }
                .create()
                .show()
            return true
        }
        return false
    }
}


