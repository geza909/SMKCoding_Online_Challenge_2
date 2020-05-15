package com.example.covid_19

import Fragment.HomeFragment
import Fragment.NewsFragment
import Fragment.ProfilFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (fragmentActivity: FragmentActivity ) :
        FragmentStateAdapter(fragmentActivity) {
    private val JUMLAH_MENU = 3

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> { return HomeFragment()
            }
            1 -> {return NewsFragment()
            }
            2 -> {return ProfilFragment()
            }
            else -> {
                return HomeFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}