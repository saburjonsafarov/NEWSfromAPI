package tj.saburjonsafarov.newsfromapi.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.repository.MainRepository
import tj.saburjonsafarov.newsfromapi.repository.adapters.ViewPagerAdapter

class SwipeFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var category: ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_swipe, container, false)
        viewPager = view.findViewById(R.id.swipeFragmentViewPager)
        tabLayout = view.findViewById(R.id.swipeFragmentTabLayout)
        return view
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = ArrayList<String>()
        MainRepository.categories.forEach { el ->
            if (el.isChecked)
                categories.add(el.category)
        }

        viewPager.adapter =
            ViewPagerAdapter(
                categories,
                requireActivity().supportFragmentManager,
                lifecycle
            )
        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->
            categories.forEachIndexed { index, el ->
                if (position == index)
                    tab.text = el
            }
        }.attach()

    }

}
