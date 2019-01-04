package com.example.menu.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.menu.R;

public class Topbar extends RelativeLayout {

    private Button leftButton,rightButton;
    private TextView tvTitle;

    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;
    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;
    private float titleTextSize;
    private int titleTextColor;
    private String title;

    private LayoutParams leftParams,rightParams,titleParams;

    private topbarClickListener listener;//这个变量映射调用者传进来的接口
    public interface topbarClickListener{
        public void leftClick();
        public void rightClick();
    }
    public void setOnTopbarClickListener(topbarClickListener listener){
        this.listener=listener;
    }

    public Topbar(Context context, AttributeSet attrs) {//第二个参数是自定义属性
        super(context, attrs);
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.Topbar);//映射
        leftTextColor=ta.getColor(R.styleable.Topbar_leftTextColor,0);//里面是key
        leftBackground=ta.getDrawable(R.styleable.Topbar_leftBackground);
        leftText=ta.getString(R.styleable.Topbar_leftText);

        rightTextColor=ta.getColor(R.styleable.Topbar_rightTextColor,0);
        rightBackground=ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightText=ta.getString(R.styleable.Topbar_rightText);

        titleTextSize=ta.getDimension(R.styleable.Topbar_titleTextSize,0);
        titleTextColor=ta.getColor(R.styleable.Topbar_titleTextColor,0);
        title=ta.getString(R.styleable.Topbar_title);
        //此时,我们在类里自定义的属性已经通过映射与自定义属性关联了
        ta.recycle();//???   回收,避免浪费资源,避免一些由于缓存引起的错误

        //下面是把属性赋给这些控件
        leftButton=new Button(context);
        rightButton=new Button(context);
        tvTitle=new TextView(context);

        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);

        setBackgroundColor(0xFFF59563);
        //把控件放入layout中
        //设置左按钮的布局
        leftParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);//添加规格
        addView(leftButton,leftParams);

        rightParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightButton,rightParams);//用ViewGroup的addView()方法添加到ViewGroup中

        titleParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(tvTitle,titleParams);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }
    //设置左按钮是否可见
    public void setLeftIsvisable(boolean flag){
        if(flag){
            leftButton.setVisibility(View.VISIBLE);
        }else {
            leftButton.setVisibility(View.GONE);
        }
    }
}
