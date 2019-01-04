package com.example.menu.slidingmenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.nineoldandroids.view.ViewHelper;

public class SildingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mScreenWidth;
    private int mMenuRightPadding=80;
    private boolean once=false;
    private int mMenuWidth;
    private boolean isOpen;

    public SildingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth=outMetrics.widthPixels;

        mMenuRightPadding= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,80,context.getResources().getDisplayMetrics());//把50dp转化为一个像素值,把dp转化为px
    }

    @Override//设置子View的宽和高,设置自己的宽和高
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once){
            mWapper=(LinearLayout)getChildAt(0);
            mMenu= (ViewGroup) mWapper.getChildAt(0);
            mContent= (ViewGroup) mWapper.getChildAt(1);
            mMenuWidth=mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
            mContent.getLayoutParams().width=mScreenWidth;
            once=true;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override//通过设置偏移量,将menu隐藏
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(mMenuWidth,0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        switch (action){
            case MotionEvent.ACTION_UP:
               int scrollX =getScrollX();//隐藏在左边的宽度
                if(scrollX>=mMenuWidth/2){
                    this.smoothScrollTo(mMenuWidth,0);
                    isOpen=false;
                }else {
                    this.smoothScrollTo(0,0);
                    isOpen=true;
                }
                return true;
        }

        return super.onTouchEvent(ev);
    }

    public void openMenu(){
        if(isOpen)return;
        this.scrollTo(0,0);
        isOpen=true;
    }
    public void closeMenu(){
        if(!isOpen)return;
        this.scrollTo(mMenuWidth,0);
        isOpen=false;
    }

    public void toggle(){
        if (isOpen){
            closeMenu();
        }else {
            openMenu();
        }
    }

    @Override//这个l就是getScrollX()
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale=l*1.0f/mMenuWidth;//1`10

        float rightScale=0.7f+0.3f*scale;
        float leftScale=1.0f-scale*0.3f;
        float leftAlpha=0.6f+0.4f*(1-scale);
        //调用属性动画,设置TranslationX
        ViewHelper.setTranslationX(mMenu,mMenuWidth*scale*0.7f);
        ViewHelper.setScaleX(mMenu,leftScale);
        ViewHelper.setScaleY(mMenu,leftScale);
        ViewHelper.setAlpha(mMenu,leftAlpha);
        //设置content的缩放的中心点
        ViewHelper.setPivotX(mContent,0);
        ViewHelper.setScaleY(mContent,mContent.getHeight()/2);
        ViewHelper.setScaleX(mContent,rightScale);
        ViewHelper.setScaleY(mContent,rightScale);

    }
}
