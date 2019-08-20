# PagerIndicatorView

1、[体验demo](https://github.com/zhaozohar/PagerIndicatorView/tree/master/apk)

2、`截图`
<br/>
<img src="https://github.com/zhaozohar/PagerIndicatorView/blob/master/picture/v200/pager1.png" width=216 height=384 />
<img src="https://github.com/zhaozohar/PagerIndicatorView/blob/master/picture/v200/pager2.png" width=216 height=384 />

3、`使用`
 <br/>
    
    在布局文件中添加pagerindicator
   
    <com.xiaoniu.pagerindicator.PagerIndicatorView
            android:id="@+id/piv_indicator"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerHorizontal="true"
            android:layout_alignParentBottom="true"
            android:layout_marginBottom="64dp"/>
            
     PagerIndicatorView初始化：         
       ```  pagerIndicatorView
                        .setCount(ids.size())
                        .setMargin(9)
                        .setNormalDotSize(9,9)
                        .setNormalDotColor(pagerIndicatorView.getContext().getResources().getColor(R.color.colorPrimaryDark))
        //                .setScrollDotWidth(9,9) //可以跟正常点的大小一样
                        .setScrollDotWidth(24,9)
                        .setScrollDotColor(pagerIndicatorView.getContext().getResources().getColor(R.color.colorAccent))
                        .show();```
  
  PagerIndicatorView的选中图片随着viewpager的滚动而移动：
  ``` viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
           }); ```