package com.slidingceilingsample.fragment

import androidx.fragment.app.Fragment
import com.slidingceilingsample.callback.FirstVisibleItemPositionListener

/**
 * @author: chenpengpeng
 * @date: 19-8-15
 * @desc:
 */
open class BaseFragment : Fragment(){
    lateinit var mListener : FirstVisibleItemPositionListener
    fun setListener(listener: FirstVisibleItemPositionListener){
        this.mListener = listener
    }
}
