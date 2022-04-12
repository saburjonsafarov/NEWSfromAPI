package tj.saburjonsafarov.newsfromapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.repository.model.EverythingModel.Articles

class DetailFragment : Fragment() {
    lateinit var textView: TextView
    lateinit var datas: Articles
    lateinit var imageView: ImageView
    lateinit var publishedAt: TextView
    lateinit var title: TextView
    lateinit var description: TextView
    lateinit var content: TextView
    lateinit var url: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        imageView = view.findViewById(R.id.detailImageView)
        publishedAt = view.findViewById(R.id.detailPublishedAt)
        title = view.findViewById(R.id.detailTitle)
        content = view.findViewById(R.id.detailContent)
        url = view.findViewById(R.id.detailUrl)
        description = view.findViewById(R.id.detailDescription)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(datas.urlToImage)
            .into(imageView)
        publishedAt.text = datas.publishedAt
        title.text = datas.title
        description.text = datas.description
        content.text = datas.content
        url.text = datas.url

    }

    companion object {
        fun newInstance(ar: Articles): DetailFragment {
            val fragment = DetailFragment()
            fragment.datas = ar
            return fragment
        }
    }

}