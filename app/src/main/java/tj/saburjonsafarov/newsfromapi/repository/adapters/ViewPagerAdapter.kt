package tj.saburjonsafarov.newsfromapi.repository.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import tj.saburjonsafarov.newsfromapi.view.HomeFragment

class ViewPagerAdapter(val array:ArrayList<String>,fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return array.size
    }

    override fun createFragment(position: Int): Fragment {

        array.forEachIndexed() { index,el ->
            if (position == index){
                return HomeFragment.newInstance(el)
            }
        }
        return HomeFragment.newInstance("Sport")
    }
}