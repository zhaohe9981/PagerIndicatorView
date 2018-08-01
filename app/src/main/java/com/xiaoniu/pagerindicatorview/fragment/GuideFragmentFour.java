package com.xiaoniu.pagerindicatorview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoniu.pagerindicatorview.R;


/**
 * @author xiaoniu
 * @date 2018/7/23.
 */

public class GuideFragmentFour extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        ((TextView)view.findViewById(R.id.tv_pager)).setText("pager 4");
        Button btn_open = view.findViewById(R.id.btn_open);
        btn_open.setVisibility(View.VISIBLE);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
//                getActivity().finish();
            }
        });
        return view;
    }

}
