package com.example.menu.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.menu.Adapter.MyAdapter;
import com.example.menu.R;
import com.example.menu.Utils.MyItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

public class RecycleView_test extends Activity implements MyItemClickListener{

    private ArrayList<HashMap<String ,Object>> listItem;
    private MyAdapter adapter;
    private RecyclerView rv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_test);
        initData();
        initView();
    }

    private void initView() {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);//这个布局是每个item的布局
       /* GridLayoutManager layoutManager = new GridLayoutManager(this,3);//使用GridLayout布局*/
        rv_test = findViewById(R.id.rv_tset);
        adapter=new MyAdapter(this,listItem);
        adapter.setOnItemClickListener(this);
        rv_test.setLayoutManager(layoutManager);//没有布局直接不显示
        rv_test.setAdapter(adapter);

    }

    private void initData() {
        listItem=new ArrayList<HashMap<String ,Object>>();
        for (int i = 0; i < 100; i++) {
            HashMap<String ,Object> map=new HashMap<String, Object>();
            map.put("ItemTitle","第" + i + "行");
            map.put("ItemText","这是第" + i + "行");
            map.put("ItemImage",R.mipmap.ic_launcher);
            listItem.add(map);
        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        Log.i("2333","点击了第"+postion+"行");
        Toast.makeText(this, (String)listItem.get(postion).get("ItemText"), Toast.LENGTH_SHORT).show();
    }
}
