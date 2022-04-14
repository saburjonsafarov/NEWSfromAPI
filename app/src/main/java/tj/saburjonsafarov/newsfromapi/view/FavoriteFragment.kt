package tj.saburjonsafarov.newsfromapi.view

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.repository.DBHelper
import tj.saburjonsafarov.newsfromapi.repository.adapters.MainAdapter
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel
import tj.saburjonsafarov.newsfromapi.vm.FavoriteViewModel

@Deprecated("zameneno na basefragment")
class FavoriteFragment : Fragment(), View.OnLongClickListener, View.OnClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: FavoriteViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this)[FavoriteViewModel(Application())::class.java]
            .also { viewModel = it }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        recyclerView = view.findViewById(R.id.favoritesRecyclerView)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getFavorites().observe(viewLifecycleOwner) {


            if (it != null) {
                val adapter = MainAdapter(it, requireContext(), this, this)
                recyclerView.adapter = adapter

            } else {
                Toast.makeText(context, "you are haven't any favorites !", Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }

    override fun onLongClick(p0: View?): Boolean {
        p0?.let {
            AlertDialog
                .Builder(requireContext())
                .setIcon(R.drawable.ic_baseline_delete_forever_24)
                .setTitle("delete new from favorites")
                .setMessage("delete all news or only this new ?")
                .setPositiveButton("this") { _, _ ->

                    delete()
                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, FavoriteFragment())
                        .commit()

                    Toast.makeText(context, "cleared !", Toast.LENGTH_SHORT).show()

                }
                .setNegativeButton("all") { _, _ ->
                    delete()
                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, FavoriteFragment())
                        .commit()

                    Toast.makeText(context, "cleared !", Toast.LENGTH_SHORT).show()
                }
                .create()
                .show()
            return true
        }

        return false
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
        }
    }

    private fun delete() {
        DBHelper(requireContext())
            .delete(DBHelper.FAVORITES_TABLE, "")
    }

}
