# PagerIndicatorView

1、[体验demo](../apk)

2、`截图`
<br/>
<img src="../picture/v100/pic1.png" width=216 height=384 />
<img src="../picture/v100/pic2.png" width=216 height=384 />

3、`使用`
 <br/>
    添加gradle依赖:    api 'com.xiaoniu:pagerindicator:1.0.0'
    
    在布局文件中添加pagerindicator
    
    <com.xiaoniu.pagerindicator.PagerIndicatorView
            android:id="@+id/pager_dots"
            android:layout_width="wrap_content"
            android:layout_centerHorizontal="true"
            android:layout_alignParentBottom="true"
            android:layout_height="60dp"
            android:layout_marginBottom="50dp"
            app:default_icon="@drawable/guide_indicator_default"
            app:selected_icon="@drawable/guide_indicator_selected"
            app:itemCount="4"
            app:icon_right_margin="10dp" />
    
  PagerIndicatorView 的属性有：
   
       <declare-styleable name="PagerIndicatorView">
           <!--点的个数-->
           <attr name="itemCount" format="integer"/>
           <!--默认显示的icon-->
           <attr name="default_icon" format="reference"/>
           <!--选中的icon-->
           <attr name="selected_icon" format="reference"/>
           <!--icon的右边距-->
           <attr name="icon_right_margin" format="dimension"/>
       </declare-styleable>
  
  PagerIndicatorView的选中图片随着viewpager的滚动而移动：
  ``` viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
               @Override
               public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                   pager_dots.onPageScrolled(position, positionOffset);
               }
   
               @Override
               public void onPageSelected(int position) {
          
               }
   
               @Override
               public void onPageScrollStateChanged(int state) {
   
               }
           });```