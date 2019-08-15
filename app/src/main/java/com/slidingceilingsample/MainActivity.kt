package com.slidingceilingsample

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.os.Bundle
import android.view.Surface
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.slidingceilingsample.adapter.SlidingFragmentPagerAdapter
import com.slidingceilingsample.callback.FirstVisibleItemPositionListener
import com.slidingceilingsample.fragment.FirstFragment
import com.slidingceilingsample.fragment.SecondFragment
import com.slidingceilingsample.fragment.ThirdFragment
import com.slidingceilingsample.view.CustomerNestedScrollView
import kotlinx.android.synthetic.main.activity_main.*
import kotterknife.bindView
import android.R.attr.data
import androidx.core.os.HandlerCompat.postDelayed
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout



class MainActivity : AppCompatActivity(){
    private var mHeaderHeight : Int = 0
    private  val mHeadView : View by bindView<View>(R.id.crm_goods_comment_order)
    private val mViewpager : ViewPager by bindView<ViewPager>(R.id.crm_single_item_pager)
    private val mTabLayout : TabLayout by bindView<TabLayout>(R.id.tabLayout)
    private val mNestedScrollView : CustomerNestedScrollView by bindView<CustomerNestedScrollView>(R.id.scrollView)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initResourceRefs()
    }

    private fun initResourceRefs(){
        mNestedScrollView.isSmoothScrollingEnabled = true
        mHeadView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                mHeadView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                mNestedScrollView.setHeaderHeight(mHeadView.height)

            }
        })
        val listener = FirstVisibleItemPositionListener {
            mNestedScrollView.fling(0)
            mNestedScrollView.smoothScrollTo(0, 0)
        }

        val firstFragment  = FirstFragment()
        firstFragment.setListener(listener)
        val secondFragment = SecondFragment()
        secondFragment.setListener(listener)
        val thirdFragment = ThirdFragment()
        thirdFragment.setListener(listener)

        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(firstFragment)
        fragmentList.add(secondFragment)
        fragmentList.add(thirdFragment)

        mViewpager.adapter =
            SlidingFragmentPagerAdapter(supportFragmentManager, fragmentList)
        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.addTab(mTabLayout.newTab())

        mTabLayout.setupWithViewPager(mViewpager)
        mTabLayout.getTabAt(0)?.setText("动态")
        mTabLayout.getTabAt(1)?.setText("消息")
        mTabLayout.getTabAt(2)?.setText("其他")
        mTabLayout.post(Runnable {
            val params = mViewpager.layoutParams
            params.height =
                getScreenRect(this@MainActivity).height() - mTabLayout.getMeasuredHeight()
            mViewpager.layoutParams = params
        })

    }



    private fun getScreenRect(context: Context): Rect {

        val w = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = w.defaultDisplay
        val rotation = display.rotation

        val rect = Rect()

        val point = Point()
        display.getSize(point)
        if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
            rect.bottom = point.y
            rect.right = point.x
        } else {
            rect.bottom = point.x
            rect.right = point.y
        }

        return rect
    }
}
