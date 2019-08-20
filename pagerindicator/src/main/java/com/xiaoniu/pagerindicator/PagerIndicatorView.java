package com.xiaoniu.pagerindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


/**
 * @author: hezhao
 * @data: 2019-08-20
 * @describe: pager 指示器
 */
public class PagerIndicatorView extends View {

    public PagerIndicatorView(Context context) {
        this(context, null);
    }

    public PagerIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        initPaint();
    }

    private Paint mNormalDotPaint;
    private Paint mScrollDotPaint;

    /**点的数量*/
    private int dotCounts;
    /**正常的点宽度*/
    private int normalDotWidth;
    private int normalDotHeight;
    /**滑动的点宽度*/
    private int scrollDotWidth;
    private int scrollDotHeight;
    /**点之间的距离*/
    private int dotMargin;
    /**控件的宽高*/
    private int viewWidth;
    private int viewHeight;

    /**可以滑动的view滑动的距离*/
    private int scrollInstance;
    /**可滑动的view所在的位置*/
    private int scrollPosition;

    private void initPaint() {
        //初始化画笔
       mNormalDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
       mNormalDotPaint.setColor(Color.parseColor("#4097F3"));
       mNormalDotPaint.setStyle(Paint.Style.FILL);

       mScrollDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
       mScrollDotPaint.setColor(Color.parseColor("#4097F3"));
       mScrollDotPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 设置点的数量
     * @param count
     * @return
     */
    public PagerIndicatorView setCount(int count){
        this.dotCounts = count;
        return this;
    }

    /**
     * 正常点的大小
     * @param width
     */
    public PagerIndicatorView setNormalDotSize(int width, int height){
        this.normalDotWidth = dip2px(getContext(), width);
        this.normalDotHeight = dip2px(getContext(), height);
        return this;
    }

    /**
     * 正常点的颜色
     * @param color
     */
    public PagerIndicatorView setNormalDotColor(int color){
        mNormalDotPaint.setColor(color);
        return this;
    }

    /**
     * 滑动点的颜色
     * @param color
     */
    public PagerIndicatorView setScrollDotColor(int color){
        mScrollDotPaint.setColor(color);
        return this;
    }

    /**
     * 滑动点的大小
     * @param width
     */
    public PagerIndicatorView setScrollDotWidth(int width, int height){
        this.scrollDotWidth = dip2px(getContext(), width);
        this.scrollDotHeight = dip2px(getContext(), height);
        return this;
    }

    /**
     * 点之间的距离
     * @param margin
     */
    public PagerIndicatorView setMargin(int margin){
        this.dotMargin = dip2px(getContext(), margin);
        return this;
    }

    /**
     * 显示控件
     */
    public void show(){
        if (scrollDotWidth < normalDotWidth){
            scrollDotWidth = normalDotWidth;
        }
        viewWidth = (dotCounts - 1) * normalDotWidth + (dotCounts -1) * dotMargin + scrollDotWidth;
        viewHeight = Math.max(normalDotHeight, scrollDotHeight);
        requestLayout();//view 重走 onMeasure, onCanvas
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (dotCounts <= 0){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }else {
            setMeasuredDimension(viewWidth, viewHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dotCounts <= 1){
            return;
        }
        normalDot(canvas);
        scrollDot(canvas);
    }

    /**
     * 初始化正常的点
     * @param canvas
     */
    private void normalDot(Canvas canvas) {
        //画第正常点,初始化坐标
        float radius = normalDotWidth / 2.0f;
        float cx = getPaddingLeft() + radius;
        float cy = getMeasuredHeight()/2.0f;

        for (int i = 0; i <dotCounts; i++){
            canvas.drawCircle(cx, cy, radius, mNormalDotPaint);
            //如果
            if (i == scrollPosition){
                cx = cx + scrollDotWidth + dotMargin;
            }else {
                cx = cx + dotMargin + normalDotWidth;
            }
        }

    }

    /**
     * 显示可滑动的点
     * @param canvas
     */
    private void scrollDot(Canvas canvas) {
        //画滑动的点
        float radius = normalDotWidth / 2.0f;
        int left = getPaddingLeft() + scrollInstance;
        RectF rectF = new RectF(left,
                getPaddingTop(),
                left + scrollDotWidth,
                getPaddingTop() + getHeight());
        canvas.drawRoundRect(rectF,
                radius,
                radius,
                mScrollDotPaint);
    }



    /**
     *  效果是： 选中滑块随着页面的滑动而滑动
     *  注意不要与onPageSelected同时调用
     * @param position
     * @param positionOffset
     */
    public void onPageScrolled(int position, float positionOffset) {
        this.scrollPosition = position;
        scrollInstance = (int)((position + positionOffset) * (dotMargin + normalDotWidth));
        postInvalidate();
    }

    /**
     * 效果是： 页面滚动停止之后选中滑块显示在position位置
     * 注意不要与onPageScrolled同时调用
     * @param position
     */
    public void onPageSelected(int position) {
        this.scrollPosition = position;
        scrollInstance = position * (dotMargin + normalDotWidth);
        postInvalidate();
    }



    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(Context context, int dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    private int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}

