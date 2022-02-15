package com.gdev.moneymanager.Sliders

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.gdev.moneymanager.R

class Slider1 : Fragment() {

    lateinit var imageView: ImageView
    lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       var view =  inflater.inflate(R.layout.fragment_slider1, container, false)

        imageView = view.findViewById(R.id.image_slide1_res)
        linearLayout = view.findViewById(R.id.linear_slider_text_1)

        var displaymatrix = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displaymatrix)
        var x  = displaymatrix.widthPixels / 2 - 10


        var params = imageView.layoutParams
        params.height = x * 2
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        imageView.requestLayout()

         params =  linearLayout.layoutParams
        params.height = x
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        linearLayout.requestLayout()

        return view
    }

    companion object {

    }
}