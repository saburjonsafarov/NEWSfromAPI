package tj.saburjonsafarov.newsfromapi.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import tj.saburjonsafarov.newsfromapi.R
import tj.saburjonsafarov.newsfromapi.databinding.FragmentSwipeBinding

class SwipeFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var binding: FragmentSwipeBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = FragmentSwipeBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        viewPager = binding.SwipeFragmentViewPager
        tabLayout = binding.SwipeFragmentTabLayout
        return inflater.inflate(R.layout.fragment_swipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    companion object{
        fun newInstance(){

        }
    }

}
