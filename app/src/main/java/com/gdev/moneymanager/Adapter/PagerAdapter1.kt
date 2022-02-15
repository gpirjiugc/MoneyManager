package com.gdev.moneymanager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.gdev.moneymanager.Sliders.Slider1
import com.gdev.moneymanager.Sliders.Slider2
import com.gdev.moneymanager.Sliders.Slider3
import com.gdev.moneymanager.Sliders.Slider4


class PagerADapter1 (fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return Slider1() //ChildFragment1 at position 0
            1 -> return Slider2() //ChildFragment2 at position 1
            2 -> return Slider3()
            3 -> return Slider4()//ChildFragment3 at position 2
        }
        return Slider1()
    }



    override fun getCount(): Int {
        return 4 //three fragments
    }

}
