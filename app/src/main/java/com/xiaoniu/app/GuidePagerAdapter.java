package com.xiaoniu.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author: hezhao
 * @data: 2019-08-20
 * @describe:
 */
public class GuidePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public GuidePagerAdapter(List<Fragment> list, FragmentManager fm) {
        super(fm);
        this.fragments = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
