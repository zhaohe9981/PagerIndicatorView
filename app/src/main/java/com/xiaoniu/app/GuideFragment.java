package com.xiaoniu.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author: hezhao
 * @data: 2019-08-20
 * @describe: 引导页fragment
 */
public class GuideFragment extends Fragment {

    public static final String KEY_PAGER_LAYOUT = "KEY_PAGER_LAYOUT";

    private int layoutID;


    public static GuideFragment getInstance(int layoutID){
        GuideFragment fragment = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PAGER_LAYOUT, layoutID);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutID = getArguments().getInt(KEY_PAGER_LAYOUT, R.layout.guide_pager_one);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(layoutID, null);
    }
}
