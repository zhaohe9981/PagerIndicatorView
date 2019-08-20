package com.xiaoniu.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xiaoniu.pagerindicator.PagerIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaohe
 * @date 2019.8.20
 */
public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PagerIndicatorView pagerIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.vp_guide);
        pagerIndicatorView = findViewById(R.id.piv_indicator);

        List<Integer> ids = new ArrayList<Integer>(4);
        ids.add(R.layout.guide_pager_one);
        ids.add(R.layout.guide_pager_two);
        ids.add(R.layout.guide_pager_three);
        ids.add(R.layout.guide_pager_four);

        List<Fragment> fragments = new ArrayList<>(ids.size());
        for (int layoutID :ids){
            fragments.add(GuideFragment.getInstance(layoutID));
        }

        viewPager.setAdapter(new GuidePagerAdapter(fragments, getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pagerIndicatorView.onPageScrolled(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pagerIndicatorView
                .setCount(ids.size())
                .setMargin(9)
                .setNormalDotSize(9,9)
                .setNormalDotColor(pagerIndicatorView.getContext().getResources().getColor(R.color.colorPrimaryDark))
//                .setScrollDotWidth(9,9) //可以跟正常点的大小一样
                .setScrollDotWidth(24,9)
                .setScrollDotColor(pagerIndicatorView.getContext().getResources().getColor(R.color.colorAccent))
                .show();
    }
}
