package com.xiaoniu.pagerindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author xiaoniu
 * @date 2018/7/30.
 */

public class PagerIndicatorView extends RelativeLayout{

    /**圆点的个数*/
    private int dotNum = 4;
    /**默认的icon*/
    private int default_icon_id;
    /**选中的icon*/
    private int select_icon_id;
    private int icon_right_margin = 20;
    /**圆点之间的距离*/
    private int mDistance;

    private LinearLayout indicatorLayout;
    private ImageView selected_dots;

    public PagerIndicatorView(Context context) {
        this(context, null);
    }

    public PagerIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PagerIndicatorView);
        if (typedArray != null){
            dotNum = typedArray.getInteger(R.styleable.PagerIndicatorView_itemCount, 4);
            default_icon_id = typedArray.getResourceId(R.styleable.PagerIndicatorView_default_icon, -1);
            select_icon_id = typedArray.getResourceId(R.styleable.PagerIndicatorView_selected_icon, -1);
            icon_right_margin = (int) typedArray.getDimension(R.styleable.PagerIndicatorView_icon_right_margin,
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
        }
        initView();
    }

    private void initView() {
        /**添加圆点父布局*/
        indicatorLayout = new LinearLayout(getContext());
        addView(indicatorLayout);
        /**添加选中的圆点*/
        selected_dots = new ImageView(getContext());
        selected_dots.setImageResource(select_icon_id);
        addView(selected_dots);
        /**计算两点之间的距离*/
        post(new Runnable() {
            @Override
            public void run() {
                if (dotNum > 1){
                    mDistance = indicatorLayout.getChildAt(1).getLeft() - indicatorLayout.getChildAt(0).getLeft();
                }
            }
        });
        /**开始向点的父布局添加点*/
        ImageView defaultDot = null;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, icon_right_margin, 0);
        for (int i = 0; i < dotNum; i++) {
            defaultDot = new ImageView(getContext());
            defaultDot.setImageResource(default_icon_id);
            indicatorLayout.addView(defaultDot, layoutParams);
        }
    }

    /**
     *  效果是： 选中滑块随着页面的滑动而滑动
     *  注意不要与onPageSelected同时调用
     * @param position
     * @param positionOffset
     */
    public void onPageScrolled(int position, float positionOffset) {
        float leftMargin = mDistance * (position + positionOffset);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) selected_dots.getLayoutParams();
        params.leftMargin = (int) leftMargin;
        selected_dots.setLayoutParams(params);
    }

    /**
     * 效果是： 页面滚动停止之后选中滑块显示在position位置
     * 注意不要与onPageScrolled同时调用
     * @param position
     */
    public void onPageSelected(int position) {
        float leftMargin = mDistance * position;
        LayoutParams params = (LayoutParams) selected_dots.getLayoutParams();
        params.leftMargin = (int) leftMargin;
        selected_dots.setLayoutParams(params);
    }

}
