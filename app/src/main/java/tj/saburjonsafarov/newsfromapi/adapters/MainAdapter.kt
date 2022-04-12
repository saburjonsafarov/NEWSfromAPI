package tj.saburjonsafarov.newsfromapi.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel.Articles

class MainAdapter(private var data: ArrayList<Articles>, var context: Context, private val onLongClickListener: View.OnLongClickListener?, private val onClickListener: View.OnClickListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val state = data[position]

        Glide.with(context)
            .asBitmap()
            .load(state.urlToImage)
            .into(holder.image)

        holder.title.text = state.title
        holder.description.text = state.description
        holder.cardView.tag = state
        holder.cardView.setOnClickListener (onClickListener)
        holder.cardView.setOnLongClickListener(onLongClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image = view.findViewById<ImageView>(R.id.cardViewImageView)
        val title = view.findViewById<TextView>(R.id.cardViewTitleTextView)
        val description = view.findViewById<TextView>(R.id.cardViewDescriptionTextView)
        val cardView = view.findViewById<CardView>(R.id.cardViewDetail)

    }

}