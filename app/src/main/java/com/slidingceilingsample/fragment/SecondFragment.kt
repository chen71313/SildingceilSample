package com.slidingceilingsample.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.slidingceilingsample.R
import com.slidingceilingsample.adapter.Fragment1viewAdapter
import com.slidingceilingsample.adapter.Fragment2ViewAdapter
import com.slidingceilingsample.model.SomeDataInfo
import kotterknife.bindView


class SecondFragment : BaseFragment() {
    private val mSwipeRefreshLayout : SwipeRefreshLayout by bindView<SwipeRefreshLayout>(R.id.fragment2_refresh_layout)
    private val mRecyclerView : RecyclerView by bindView<RecyclerView>(R.id.fragment2_experience_list)
    private var list : ArrayList<SomeDataInfo> = ArrayList()
    private lateinit var mAdapter: Fragment2ViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (i in 0 until 100) {
            val someDataInfo = SomeDataInfo()
            someDataInfo.title = "第二个吸顶列表"+i;
            list.add(someDataInfo)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initResourceRefs()
    }

    private fun initResourceRefs(){
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.setNestedScrollingEnabled(true)
        mAdapter = Fragment2ViewAdapter(context, list)
        mRecyclerView.adapter = mAdapter
        mSwipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            Handler().postDelayed(Runnable {
                Toast.makeText(context, "下拉刷新", Toast.LENGTH_SHORT).show()
                mAdapter.notifyDataSetChanged()
                mSwipeRefreshLayout.isRefreshing = false
            }, 2000)
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second_layout, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}
