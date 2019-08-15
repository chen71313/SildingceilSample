package com.slidingceilingsample.adapter;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author: chenpengpeng
 * @date: 2019/3/11
 * @desc:
 */
public class SlidingFragmentPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fragmentManager;  //创建FragmentManager
    private List<Fragment> listfragment; //创建一个List<Fragment>
    public SlidingFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        fragmentManager = fm;
        listfragment = list;
    }

    @Override
    public Fragment getItem(int position) {
        return listfragment.get(position);
    }

    @Override
    public int getCount() {
        return listfragment.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        this.fragmentManager.beginTransaction().show(fragment).commit();
        return fragment;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = listfragment.get(position);
        fragmentManager.beginTransaction().hide(fragment).commit();
    }
}
