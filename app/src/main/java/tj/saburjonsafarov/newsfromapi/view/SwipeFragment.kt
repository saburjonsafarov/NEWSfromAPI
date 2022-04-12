package tj.saburjonsafarov.newsfromapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.repository.adapters.ViewPagerAdapter

class SwipeFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_swipe, container, false)
        viewPager = view.findViewById(R.id.swipeFragmentViewPager)
        tabLayout = view.findViewById(R.id.swipeFragmentTabLayout)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val array = arrayListOf(
            "Sport",
            "Google",
            "IPhone",
            "Samsung",
            "Business",
            "Politics"
        )

        viewPager.adapter =
            ViewPagerAdapter(array, requireActivity().supportFragmentManager, lifecycle)
        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->
            array.forEachIndexed { index, el ->
                if (position == index)
                    tab.text = el
            }
        }.attach()

    }

    companion object {
        fun newInstance() {

        }
    }

}
