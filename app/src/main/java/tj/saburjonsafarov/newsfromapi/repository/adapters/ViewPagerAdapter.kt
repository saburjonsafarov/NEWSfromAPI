package tj.saburjonsafarov.newsfromapi.repository.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import tj.saburjonsafarov.newsfromapi.view.HomeFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 -> {
                return HomeFragment.newInstance("Sport")
            }
            1 -> {
                return HomeFragment.newInstance("Google")
            }

            2 -> {
                return HomeFragment.newInstance("IPhone")
            }
            3 -> {
                return HomeFragment.newInstance("Samsung")
            }
            4 ->{
                return HomeFragment.newInstance("Business")
            }
            5 ->{
                return HomeFragment.newInstance("Politics")
            }
        }
        return HomeFragment.newInstance("Sport")
    }
}