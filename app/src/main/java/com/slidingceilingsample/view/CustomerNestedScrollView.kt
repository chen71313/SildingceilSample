package com.slidingceilingsample.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.widget.NestedScrollView

class CustomerNestedScrollView : NestedScrollView {


    internal var mHeaderHeight: Int = 0
    internal var mScrollY: Int = 0

    constructor(context: Context) : super(context, null) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(target, dx, dy, consumed, type)
        if (dy > 0 && mScrollY < mHeaderHeight) {
            Log.d("CPP", "mScrollY < mHeaderHeight")
            consumed[0] = dx
            consumed[1] = dy
            scrollBy(0, dy)
        }
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        Log.d("CPP", "onNestedPreFling")

        if (mScrollY > 0 && mScrollY < mHeaderHeight) {
            fling(velocityY.toInt())
            return true
        } else {
            return super.onNestedPreFling(target, velocityX, velocityY)
        }
    }

    fun setHeaderHeight(scrollLength: Int) {
        Log.d("CPP", "setHeaderHeight "+scrollLength)

        this.mHeaderHeight = scrollLength
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        Log.d("CPP", "onScrollChanged "+mScrollY)

        super.onScrollChanged(l, t, oldl, oldt)
        mScrollY = t
    }
}
