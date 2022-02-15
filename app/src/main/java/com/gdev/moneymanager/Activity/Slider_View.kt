package com.gdev.moneymanager.Activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.gdev.moneymanager.PagerADapter1
import com.gdev.moneymanager.R
import com.gdev.moneymanager.databinding.ActivitySliderViewBinding
import pl.droidsonroids.gif.GifImageView


class Slider_View : AppCompatActivity() {
lateinit var viewPager: ViewPager
lateinit var  aa : AlertDialog
    private var x1 = 0f
    var x2:kotlin.Float = 0f
    val MIN_DISTANCE = 150

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> x1 = event.x
            MotionEvent.ACTION_UP -> {
                x2 = event.x
                val deltaX: Float = x2 - x1
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    aa.dismiss()
                } else {
                    // consider as something else - a screen tap for example
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding =  ActivitySliderViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.navigationBarColor = Color.parseColor("#113471")
        window.statusBarColor = Color.WHITE
        var pager = PagerADapter1(supportFragmentManager)
        viewPager  =binding.viewpager


        var imageView1 : ImageView = binding.image1Slide
        var imageView2 : ImageView = binding.image2Slide
        var imageView3 : ImageView = binding.image3Slide
        var imageView4 : ImageView = binding.image4Slide

        Handler().postDelayed(Runnable {
            val bb = AlertDialog.Builder(this)
            val ch: View = layoutInflater.inflate(R.layout.dialog_swipe_left, null)
            bb.setView(ch)
            aa = bb.create()
            aa.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            var displaymatrix = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displaymatrix)
            var x = displaymatrix.widthPixels / 2
            aa.show()
            var GifImageView = ch.findViewById<GifImageView>(R.id.gif_swipe) as GifImageView
            var params = GifImageView.layoutParams
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            params.height = x
            GifImageView.requestLayout()


        }, 1000)



        var slider_nnext = binding.sliderNext
        slider_nnext.setOnClickListener {
            startActivity(Intent(this@Slider_View, Form::class.java))
        }
        viewPager.adapter = pager

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 0) {
                    imageView1.setColorFilter(Color.parseColor("#D8D8F8"))
                    imageView2.setColorFilter(Color.parseColor("#0F61BC"))
                    imageView3.setColorFilter(Color.parseColor("#0F61BC"))
                    imageView4.setColorFilter(Color.parseColor("#0F61BC"))
                }
                if (position == 1) {
                    imageView2.setColorFilter(Color.parseColor("#D8D8F8"))
                    imageView1.setColorFilter(Color.parseColor("#0F61BC"))
                    imageView3.setColorFilter(Color.parseColor("#0F61BC"))
                    imageView4.setColorFilter(Color.parseColor("#0F61BC"))
                }
                if (position == 2) {
                    imageView3.setColorFilter(Color.parseColor("#D8D8F8"))
                    imageView2.setColorFilter(Color.parseColor("#0F61BC"))
                    imageView1.setColorFilter(Color.parseColor("#0F61BC"))
                    imageView4.setColorFilter(Color.parseColor("#0F61BC"))
                }
                if (position == 3) {
                    imageView4.setColorFilter(Color.parseColor("#D8D8F8"))
                    imageView2.setColorFilter(Color.parseColor("#0F61BC"))
                    imageView3.setColorFilter(Color.parseColor("#0F61BC"))
                    imageView1.setColorFilter(Color.parseColor("#0F61BC"))
                    slider_nnext.visibility = View.VISIBLE


                }
            }

            override fun onPageSelected(position: Int) {
                // Check if this is the page you want.
            }
        })

         }


    }

