package com.slidingceilingsample.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.slidingceilingsample.R
import com.slidingceilingsample.adapter.Fragment1viewAdapter
import com.slidingceilingsample.model.SomeDataInfo
import kotterknife.bindView

class FirstFragment : BaseFragment() {

    private val mSwipeRefreshLayout : SwipeRefreshLayout by bindView<SwipeRefreshLayout>(R.id.refresh_layout)
    private val mRecyclerView : RecyclerView by bindView<RecyclerView>(R.id.experience_list)
    private var list : ArrayList<SomeDataInfo> = ArrayList()
    private lateinit var mAdapter: Fragment1viewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (i in 0 until 100) {
            val someDataInfo = SomeDataInfo();
            someDataInfo.imageUrl = R.mipmap.ic_launcher;
            someDataInfo.desc = "吸顶列表："+i;
            list.add(someDataInfo)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initResourceRefs()
    }

    private fun initResourceRefs(){
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.isNestedScrollingEnabled = true
        mAdapter = Fragment1viewAdapter(context, list)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0) {
                    val manager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()
                    if (firstVisibleItemPosition == 0 && mListener != null) {
                        mListener.scrollTo0Position()
                    }
                }
            }
        })

        mSwipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
                Handler().postDelayed(Runnable {
                    Toast.makeText(context, "下拉刷新", Toast.LENGTH_SHORT).show()
                    mAdapter.notifyDataSetChanged()
                    mSwipeRefreshLayout.isRefreshing = false
                }, 2000)
            })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

}
