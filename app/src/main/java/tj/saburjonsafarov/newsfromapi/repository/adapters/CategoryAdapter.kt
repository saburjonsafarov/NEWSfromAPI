package tj.saburjonsafarov.newsfromapi.repository.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.repository.model.CategoryModel

class CategoryAdapter(
    private val categories: ArrayList<CategoryModel>,
    private val onClickListener: CompoundButton.OnCheckedChangeListener
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.category_item, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val state = categories[position]
        holder.checkBox.apply {
            text = state.category
            isChecked = state.isChecked
            tag = state.id
            setOnCheckedChangeListener(onClickListener)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox = view.findViewById<CheckBox>(R.id.itemCheckBox)
    }


}