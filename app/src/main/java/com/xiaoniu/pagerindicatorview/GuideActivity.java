package com.xiaoniu.pagerindicatorview;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoniu.pagerindicator.PagerIndicatorView;
import com.xiaoniu.pagerindicatorview.adapter.GuideAdapter;
import com.xiaoniu.pagerindicatorview.fragment.GuideFragmentFour;
import com.xiaoniu.pagerindicatorview.fragment.GuideFragmentOne;
import com.xiaoniu.pagerindicatorview.fragment.GuideFragmentThree;
import com.xiaoniu.pagerindicatorview.fragment.GuideFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoniu
 */
public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerIndicatorView pager_dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();
    }


    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        pager_dots = findViewById(R.id.pager_dots);
    }

    private void initData() {
        List<Fragment> fragments = new ArrayList<>(4);
        fragments.add(new GuideFragmentOne());
        fragments.add(new GuideFragmentTwo());
        fragments.add(new GuideFragmentThree());
        fragments.add(new GuideFragmentFour());
        viewPager.setAdapter(new GuideAdapter(getSupportFragmentManager(), fragments));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pager_dots.onPageScrolled(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
//                pager_dots.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
